package com.bugjc.java.basics.atomic.test.performance.service.impl;


import com.bugjc.java.basics.atomic.test.performance.service.Counter;

public class Synchronized implements Counter {

	private Object lock = new Object();
	
	private int counter;

	@Override
	public long getCounter() {
		synchronized (lock) {
			return counter;
		}
	}

	@Override
	public void increment() {
		synchronized (lock) {
			++counter;
		}
	}
}
