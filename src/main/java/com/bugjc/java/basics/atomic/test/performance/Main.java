package com.bugjc.java.basics.atomic.test.performance;

import com.bugjc.java.basics.atomic.test.performance.core.enums.Counters;
import com.bugjc.java.basics.atomic.test.performance.service.Counter;
import com.bugjc.java.basics.atomic.test.performance.service.impl.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {
	public static long TARGET_NUMBER 	= 100000000L;			//目标计数数量
	public static int THREADS = 10;							//线程数量
	public static int ROUNDS = 10;							//循环次数
	private static String COUNTER = Counters.DIRTY.toString();//默认测试实例
	
	private static ExecutorService es;
	
	private static int round;
	private static long start;
	
	private static Boolean[] rounds;

	
	public static void main(String[] args) {

		COUNTER = Counters.STAMPED.toString();
		
		if (args.length > 0) {
			COUNTER = args[0];
		}
		
		if (args.length > 1) {
			THREADS = Integer.valueOf(args[1]);
		}
		
		if (args.length > 2) {
			ROUNDS = Integer.valueOf(args[2]);
		}
		
		if (args.length > 3) {
			TARGET_NUMBER = Long.valueOf(args[3]);
		}
		
		rounds = new Boolean[ROUNDS];
		
		log.info("Using " + COUNTER + ". threads: " + THREADS + ". rounds: " + ROUNDS + ". Target: " + TARGET_NUMBER);

		for (round = 0; round < 10; round++) {
			rounds[round] = Boolean.FALSE;
			
			Counter counter = getCounter();
			
			es = Executors.newFixedThreadPool(10);
			
			start = System.currentTimeMillis();
			
			for (int j = 0; j < THREADS; j+=2) {
				es.execute(new Reader(counter));
				es.execute(new Writer(counter));
			}
			
			try {
				es.awaitTermination(10, TimeUnit.MINUTES);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Counter getCounter() {
		Counters counterTypeEnum = Counters.valueOf(COUNTER);
		
		switch (counterTypeEnum) {
			case ADDER:
				return new Adder();
			case ATOMIC:
				return new Atomic();
			case DIRTY:
				return new Dirty();
			case RWLOCK:
				return new RWLock();
			case SYNCHRONIZED:
				return new Synchronized();
			case VOLATILE:
				return new Volatile();
			case STAMPED:
				return new Stamped();
		}
		
		return null;
	}
	
	static void publish(long end) {
		synchronized (rounds[round]) {
			if (rounds[round] == Boolean.FALSE) {
				log.info("执行时间："+String.valueOf(end-start));
				rounds[round] = Boolean.TRUE;
				
				es.shutdownNow();
			}
		}
	}
}
