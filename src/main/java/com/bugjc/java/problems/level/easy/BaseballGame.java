package com.bugjc.java.problems.level.easy;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 棒球比赛
 * @author qingyang
 * @date 2018/9/17 18:32
 */
@Slf4j
public class BaseballGame {

    public int calPoints(String[] ops) {

        String c = "C";
        String d = "D";
        String plus = "+";
        List<String> middleMark = new ArrayList<String>(Arrays.asList(ops));
        for (int i = 0; i < middleMark.size(); i++) {
            String value = middleMark.get(i);
            middleMark.remove(i);
            if (value.equals(c)){
                middleMark.remove(i - 1);
                i = i - 2;
            }else if (value.equals(d)){
                middleMark.add(i,Integer.parseInt(middleMark.get(i - 1)) * 2 + "");
            }else if (value.equals(plus)){
                if (middleMark.size() == 1){
                    middleMark.add(i,middleMark.get(i - 1));
                    continue;
                }

                middleMark.add(i, String.valueOf(Integer.parseInt(middleMark.get(i - 1)) + Integer.parseInt(middleMark.get(i - 2))));
            }else {
                middleMark.add(i,value);
            }
        }

        int sum = 0;
        for (String val : middleMark) {
            sum += Integer.parseInt(val);
        }

        return sum;
    }

    public static void main(String[] args) {
        String[] ops = new String[]{"5","2","C","D","+"};
        int sum = new BaseballGame().calPoints(ops);
        log.info("sum {}",sum);
    }

}
