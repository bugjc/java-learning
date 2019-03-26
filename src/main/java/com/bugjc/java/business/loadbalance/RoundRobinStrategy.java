package com.bugjc.java.business.loadbalance;

import java.util.*;

/**
 * 负债均衡算法：轮询法
 */
public class RoundRobinStrategy {

    private int pos = 0;
    public  String roundRobin() {
        //重新建立一個map,避免出現由於服務器上線和下線導致的並發問題
        Map<String, Integer> serverMap = new HashMap<String, Integer>(IPTable.getServers());
        //獲取ip列表list
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>(keySet);
        String server = null;
        synchronized (this) {
            if(pos >= keySet.size()){
                pos = 0;
            }
            server = keyList.get(pos);
            pos ++;
        }
        return server;
    }

    public static void main(String[] args) {
        RoundRobinStrategy robin = new RoundRobinStrategy();
        for (int i = 0; i < 20; i++) {
            String serverIp = robin.roundRobin();
            System.out.println(serverIp);
        }
    }

}
