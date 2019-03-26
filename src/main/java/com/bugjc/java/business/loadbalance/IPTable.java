package com.bugjc.java.business.loadbalance;

import java.util.HashMap;
import java.util.Map;

public class IPTable {

    private static Map<String,Integer> serverWeightMap  = new HashMap<String,Integer>();
    static{
        serverWeightMap.put("192.168.1.12", 1);
        serverWeightMap.put("192.168.1.13", 1);
        serverWeightMap.put("192.168.1.14", 2);
        serverWeightMap.put("192.168.1.15", 2);
        serverWeightMap.put("192.168.1.16", 3);
        serverWeightMap.put("192.168.1.17", 3);
        serverWeightMap.put("192.168.1.18", 1);
        serverWeightMap.put("192.168.1.19", 2);
    }

    public static void remove(String ip){
        serverWeightMap.remove(ip);
    }

    public static Map<String,Integer> getServers(){
        return serverWeightMap;
    }
}
