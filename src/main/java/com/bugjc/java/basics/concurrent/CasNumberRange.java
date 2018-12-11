package com.bugjc.java.basics.concurrent;

import java.util.concurrent.atomic.AtomicReference;

public class CasNumberRange {
    private static class IntPair{
        final int lower;
        final int upper;

        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    private final AtomicReference<IntPair> values =
            new AtomicReference<IntPair>(new IntPair(0,0));

    public int getLower() { return values.get().lower; }
    public int getUpper() { return values.get().upper; }

    public void setLower(int i){
        while (true){
            IntPair oldValue = values.get();
            if (i > oldValue.upper)
                throw new IllegalArgumentException("Can't set lower to " + i + " > upper");
            IntPair newValue = new IntPair(i,oldValue.upper);
            //更新成功退出循环
            if (values.compareAndSet(oldValue,newValue))
                return;
        }
    }

    public void setUpper(int i){
        while (true){
            IntPair oldValue = values.get();
            if (i < oldValue.lower)
                throw new IllegalArgumentException("Can't set lower to " + i + " < lower");
            IntPair newValue = new IntPair(oldValue.lower,i);
            if (values.compareAndSet(oldValue,newValue))
                return;
        }
    }

}
