/**
 * SPI 全称为 Service Provider Interface，是一种服务发现机制。
 * SPI 的本质是将接口实现类的全限定名配置在文件中，并由服务加载器读取配置文件，加载实现类。
 * 这样可以在运行时，动态为接口替换实现类。正因此特性，我们可以很容易的通过 SPI 机制为我们的程序提供拓展功能。
 */
package com.bugjc.java.basics.spi;