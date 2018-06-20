package com.bugjc.java.basics.atomic.test.performance.service.impl;

import com.bugjc.java.basics.atomic.test.performance.service.Counter;

public class Dirty implements Counter {
	private long counter;

	@Override
	public long getCounter() {
		return counter;
	}

	@Override
	public void increment() {
		++counter;
	}
}
