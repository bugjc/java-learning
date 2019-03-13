package com.bugjc.java.problems.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 * 买卖股票的最佳时机 i
 * @author qingyang
 * @date 2018/9/17 23:08
 */
@Slf4j
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {

        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                if (prices[i] < prices[j]){
                    int temp = prices[j] - prices[i];
                    //比较收益值
                    if (temp > max){
                        max = temp;
                    }

                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        //prices = new int[]{7,6,4,3,1};
        prices = new int[]{0,0,1};
        int max = new BestTimeToBuyAndSellStock().maxProfit(prices);
        log.info(String.valueOf(max));
    }

}
