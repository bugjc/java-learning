package com.bugjc.java.business.loadbalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 负债均衡算法：ip hash 算法
 */
public class IpHashStrategy {

    /**
     * 获取请求服务器地址
     * @param remoteIp 负载均衡服务器ip
     * @return
     */
    public static String ipHash(String remoteIp) {
        //重新建立一個map,避免出現由於服務器上線和下線導致的並發問題
        Map<String, Integer> serverMap = new HashMap<String, Integer>(IPTable.getServers());
        //獲取ip列表list
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>(keySet);

        int hashCode = remoteIp.hashCode();
        int serverListSize = keyList.size();
        int serverPos = hashCode % serverListSize;

        return keyList.get(serverPos);
    }

    public static void main(String[] args) {
        String ip = "192.168.1.13";
        String serverIp = ipHash(ip);
        System.out.println(serverIp);
        IPTable.remove(ip);
        serverIp = ipHash(ip);
        System.out.println(serverIp);
    }

}
