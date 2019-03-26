package com.bugjc.java.business.loadbalance;

import java.util.*;

/**
 * 负债均衡算法：随机
 */
public class RandomStrategy {

    public static String random()
    {
        //重新建立一個map,避免出現由於服務器上線和下線導致的並發問題
        Map<String, Integer> serverMap = new HashMap<String, Integer>(IPTable.getServers());
        //獲取ip列表list
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>(keySet);

        Random random = new Random();
        int randomPos = random.nextInt(keyList.size());

        return keyList.get(randomPos);
    }

    public static void main(String[] args) {
        String serverIp = random();
        System.out.println(serverIp);
    }

}
