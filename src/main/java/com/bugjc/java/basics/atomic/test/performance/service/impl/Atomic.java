package com.bugjc.java.basics.atomic.test.performance.service.impl;


import com.bugjc.java.basics.atomic.test.performance.service.Counter;

import java.util.concurrent.atomic.AtomicLong;

public class Atomic implements Counter {
	private final AtomicLong atomic = new AtomicLong();

	@Override
	public long getCounter() {
		return atomic.get();
	}

	@Override
	public void increment() {
		atomic.incrementAndGet();
	}
}
