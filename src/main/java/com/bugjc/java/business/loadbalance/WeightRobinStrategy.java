package com.bugjc.java.business.loadbalance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 负债均衡算法：加权轮询
 */
public class WeightRobinStrategy {

    private Integer pos = 0;
    public String WeightRobin(){
        Map<String, Integer> ipServerMap = new ConcurrentHashMap<>(IPTable.getServers());
        Set<String> ipSet = ipServerMap.keySet();
        Iterator<String> ipIterator = ipSet.iterator();

        //定义一个list放所有server
        ArrayList<String> ipArrayList=new ArrayList<String>();
        //循环set，根据set中的可以去得知map中的value，给list中添加对应数字的server数量
        while (ipIterator.hasNext()){
            String serverName = ipIterator.next();
            Integer weight = ipServerMap.get(serverName);
            for (int i = 0;i < weight ;i++){
                ipArrayList.add(serverName);
            }
        }
        String serverName=null;
        if (pos >= ipArrayList.size()){
            pos=0;
        }
        serverName = ipArrayList.get(pos);
        //轮询+1
        pos ++;
        return  serverName;
    }

    public static void main(String[] args) {
        WeightRobinStrategy testWeightRobin = new WeightRobinStrategy();
        for (int i = 0;i < 10; i++){
            String server = testWeightRobin.WeightRobin();
            System.out.println(server);
        }
    }

}
