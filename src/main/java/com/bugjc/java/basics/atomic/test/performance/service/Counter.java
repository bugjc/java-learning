package com.bugjc.java.basics.atomic.test.performance.service;

public interface Counter {

	/**
	 * 获取计数值
	 * @return
	 */
	long getCounter();

	/**
	 * 计数值加1
	 */
	void increment();

}
