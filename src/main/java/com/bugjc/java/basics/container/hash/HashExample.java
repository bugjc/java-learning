package com.bugjc.java.basics.container.hash;

public class HashExample {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int hash = i;
            int length = 1024;
            int hash1 = hash % length;
            int hash2 = hash & (length - 1);
            System.out.println(hash1 == hash2);
        }

    }
}
