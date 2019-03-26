package com.bugjc.java.business.loadbalance;

import java.util.*;

/**
 * 负债均衡算法：加权随机
 */
public class WeightRandomStrategy {

    public static String weightRandom(){
        //重新建立一個map,避免出現由於服務器上線和下線導致的並發問題
        Map<String, Integer> serverMap = new HashMap<String, Integer>(IPTable.getServers());
        //獲取ip列表list
        Set<String> keySet = serverMap.keySet();
        Iterator<String> it = keySet.iterator();

        List<String> serverList = new ArrayList<String>();

        while (it.hasNext()) {
            String server = it.next();
            Integer weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
             }
        }
        Random random = new Random();
        int randomPos = random.nextInt(serverList.size());
        return serverList.get(randomPos);
      }

      public static void main(String[] args) {
          String serverIp = weightRandom();
          System.out.println(serverIp);
      }


}
