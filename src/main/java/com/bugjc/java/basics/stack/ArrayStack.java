package com.bugjc.java.basics.stack;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于数组实现的顺序栈
 * 空间复杂度O(1)
 * 时间复杂度O(1)
 * @author aoki
 */
@Slf4j
public class ArrayStack {

    /**
     * 数组
     */
    private String[] items;
    /**
     * 栈中元素个数
     */
    private int count;
    /**
     * 栈的大小
     */
    private int n;

    /**
     * 初始化数组，申请一个大小为 n 的数组空间
     * @param n
     */
    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    /**
     * 入栈操作
     * @param item
     * @return
     */
    public boolean push(String item) {
        // 数组空间不够了，直接返回 false，入栈失败。
        if (count == n){
            return false;
        }
        // 将 item 放到下标为 count 的位置，并且 count 加一
        items[count] = item;
        ++count;
        return true;
    }

    /**
     * 出栈操作
     * @return
     */
    public String pop() {
        // 栈为空，则直接返回 null
        if (count == 0) {
            return null;
        }
        // 返回下标为 count-1 的数组元素，并且栈中元素个数 count 减一
        String tmp = items[count-1];
        --count;
        return tmp;
    }


    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(10);
        String stackVal;
        for (int i = 0; i < 10; i++) {
            stackVal = (i + 1)+"";
            log.info("入栈：{}",stackVal);
            arrayStack.push(stackVal);
        }

        do{
            stackVal = arrayStack.pop();
            if (stackVal != null){
                log.info("出栈：{}",stackVal);
            }
        } while (stackVal != null);
    }
}
