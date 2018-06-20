package com.bugjc.java.basics.atomic.test.performance.service.impl;



import com.bugjc.java.basics.atomic.test.performance.service.Counter;

import java.util.concurrent.atomic.LongAdder;

public class Adder implements Counter {

	private final LongAdder adder = new LongAdder();

	@Override
	public long getCounter() {
		return adder.longValue();
	}

	@Override
	public void increment() {
		adder.increment();
	}

}
