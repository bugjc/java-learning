package com.bugjc.java.basics.sequence;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qingyang
 * @date 2018/9/17 14:26
 */
@Slf4j
public class SequenceTest {


    public static void main(String[] args) {
        long id = SequenceGenerator.nextId();
        log.info("id:{}",id);

    }
}
