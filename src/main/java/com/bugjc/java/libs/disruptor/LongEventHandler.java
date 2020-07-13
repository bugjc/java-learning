package com.bugjc.java.libs.disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        System.out.println("消费者:"+event.getValue());
    }
}
