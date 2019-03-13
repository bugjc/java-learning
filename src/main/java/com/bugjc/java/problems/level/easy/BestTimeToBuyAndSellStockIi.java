package com.bugjc.java.problems.level.easy;

import lombok.extern.slf4j.Slf4j;

/**
 * 买卖股票的最佳时机 ii
 * @author qingyang
 * @date 2018/9/18 09:38
 */
@Slf4j
public class BestTimeToBuyAndSellStockIi {


    public int maxProfit(int[] prices) {

        //最大收益值
        int maximum = 0;
        for (int i = 0; i < prices.length; i++) {

            if (i != prices.length -1){
                //贪心比较,找相对小的数
                if (prices[i] >= prices[i+1]){
                    continue;
                }
            }


            for (int j = i + 1; j < prices.length; j++) {

                if (prices[i] < prices[j]){

                    if (j != prices.length -1){
                        //贪心比较，找相对大的数
                        if (prices[j] <= prices[j+1]){
                            continue;
                        }
                    }

                    maximum += prices[j] - prices[i];
                    i = j;
                    break;
                }
            }
        }

        return maximum;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        prices = new int[]{7,6,4,3,1};
        prices = new int[]{0,0,1};
        prices = new int[]{1,2,3,4,5};
        prices = new int[]{6,1,3,2,4,7};
        prices = new int[]{1,7,4,2,11};
        prices = new int[]{7,11,1,2,4};
        prices = new int[]{1,4,1,4,3,1,4,6,2,7};
        prices = new int[]{8,6,4,3,3,2,3,5,8,3,8,2,6};
        int max = new BestTimeToBuyAndSellStockIi().maxProfit(prices);
        log.info(String.valueOf(max));
    }
}
