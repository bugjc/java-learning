package com.bugjc.java.basics;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author qingyang
 * @date 2018/11/6 22:34
 */
@Slf4j
public class DemoExample {


    private int f(int n){
        if (n == 1){
            return 1;
        }
        return f(n - 1) + 1;
    }

    public static void main(String[] args) {
        int n = new DemoExample().f(9);
        log.info(n + "");


        int a = 300;
        Map<String,Object> hash = new HashMap<>();
        Map<String, List<Integer>> bucket = new HashMap<>();
        for (int i = 0; i < a; i++) {
            int max = RandomUtil.randomInt(0,5000);
            hash.put(i+"",max);
            int b = (i + max) % 10;
            log.info("桶{}存放了值{}",b,i);
            List<Integer> list = bucket.get(""+b);
            if (list == null){
                list = new ArrayList<>();
            }
            list.add(i);
            bucket.put(""+b,list);
        }

        // 查询值80存放在哪个桶
        int i = 80;
        int max = Integer.parseInt(hash.get(""+i).toString());
        int b = (i + max) % 10;
        List<Integer> list = bucket.get(""+b);
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) != null && list.get(j) == i){
                log.info("在桶{}，找到了值{}",b,i);
            }
        }
    }
}
