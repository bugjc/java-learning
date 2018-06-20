package com.bugjc.java.basics.atomic.test.performance.service.impl;


import com.bugjc.java.basics.atomic.test.performance.service.Counter;

import java.util.concurrent.locks.StampedLock;

public class Stamped implements Counter {

	//StampedLock的内部实现是基于CLH锁的,CLH锁是一种自旋锁,它保证没有饥饿的发生,并且可以保证FIFO(先进先出)的服务顺序.
	//CLH锁的基本思想如下:锁维护一个等待线程队列,所有申请锁,但是没有成功的线程都记录在这个队列中,每一个节点代表一个线程,保存一个标记位(locked).用与判断当前线程是否已经释放锁;locked=true 没有获取到锁,false 已经成功释放了锁
	private StampedLock stampedLock = new StampedLock();
	
	private long counter;

	@Override
	public long getCounter() {
		long stamp = stampedLock.tryOptimisticRead();
		long result = counter;

		if (stampedLock.validate(stamp)){ //判断这个stamp是否在读过程发生期间被修改过,如果stamp没有被修改过,责任无这次读取时有效的,因此就可以直接return了,反之,如果stamp是不可用的,则意味着在读取的过程中,可能被其他线程改写了数据,因此,有可能出现脏读,如果如果出现这种情况,我们可以像CAS操作那样在一个死循环中一直使用乐观锁,知道成功为止
			return result;
		}

		try {
			stamp = stampedLock.readLock(); //也可以升级锁的级别,这里我们升级乐观锁的级别,将乐观锁变为悲观锁, 如果当前对象正在被修改,则读锁的申请可能导致线程挂起.
			result = counter;
		} finally {
			stampedLock.unlockRead(stamp);
		}

		return result;
	}

	@Override
	public void increment() {
		long stamp = stampedLock.writeLock();
		
		try {
			++counter;
		} finally {
			stampedLock.unlockWrite(stamp);
		}
	}
}
