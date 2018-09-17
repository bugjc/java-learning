package com.bugjc.java.basics.sequence;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qingyang
 * @date 2018/9/17 14:25
 */
public class SequenceGenerator {

    private static final AtomicInteger COUNTER = new AtomicInteger(new SecureRandom().nextInt());

    private static final int TOTAL_BITS = 64;
    private static final int EPOCH_BITS = 42;
    private static final int MACHINE_ID_BITS = 10;

    private static final int MACHINE_ID;
    private static final int LOWER_ORDER_TEN_BITS = 0x3FF;
    private static final int LOWER_ORDER_TWELVE_BITS = 0xFFF;

    public static long nextId() {
        long curMs = Instant.now().toEpochMilli();
        long id = curMs << (TOTAL_BITS - EPOCH_BITS);
        id |= (MACHINE_ID << (TOTAL_BITS - EPOCH_BITS - MACHINE_ID_BITS));
        id |= (getNextCounter() & LOWER_ORDER_TWELVE_BITS);
        return id;
    }

    private static int getNextCounter() {
        return COUNTER.getAndIncrement();
    }

    static {
        MACHINE_ID = createMachineId();
    }

    private static int createMachineId() {
        int machineId;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (byte aMac : mac) {
                        sb.append(String.format("%02X", aMac));
                    }
                }
            }
            machineId = sb.toString().hashCode();
        } catch (Exception ex) {
            machineId = (new SecureRandom().nextInt());
        }
        machineId = machineId & LOWER_ORDER_TEN_BITS;
        return machineId;
    }

}
