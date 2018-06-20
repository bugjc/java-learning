package com.bugjc.java.basics.atomic.test.performance.service.impl;
import com.bugjc.java.basics.atomic.test.performance.service.Counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLock implements Counter {

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private Lock readLock = readWriteLock.readLock();
	private Lock writeLock = readWriteLock.writeLock();
	
	private long counter;
	
	public long getCounter() {
		try {
			readLock.lock();
			return counter;
		} finally {
			readLock.unlock();
		}
	}
	
	public void increment() {
		try {
			writeLock.lock();
			++counter;
		} finally {
			writeLock.unlock();
		}
	}
}
