package com.bugjc.java.lintcode.level.naive;


import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class FibonacciSequence {
    public static void main(String[] args) {
        System.out.println(new FibonacciSequence().fibonacci(10));
        System.out.println(new FibonacciSequence().fibonacciFun(10));
        // 生成前20项斐波那契数列
        // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597,
        // 2584, 4181, 6765]
        System.out.println(Arrays.toString(FibonacciSequence.generateFibonacciSequence().limit(20).toArray()));

    }

    /**
     * 查找斐波纳契数列中第 N 个数。

     所谓的斐波纳契数列是指：

     前2个数是 0 和 1 。
     第 i 个数是第 i-1 个数和第i-2 个数的和。
     斐波纳契数列的前10个数字是：

     0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...

     注意事项

     The Nth fibonacci number won't exceed the max value of signed 32-bit integer in the test cases.
     * @param n
     * @return
     */
    public int fibonacci(int n) {
        // write your code here
        if ((n == 1) || (n == 2))
            return n == 1 ? 0 : 1;

        int[] result = new int[n];
        result[0] = 0;
        result[1] = 1;
        for (int i = 2; i < n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n - 1];
    }

    /**
     * 递归方式
     *
     * @param n
     * @return
     */
    private int fibonacciFun(int n) {
        if ((n == 1) || (n == 2)){
            return n == 1 ? 0 : 1;
        }


        return fibonacciFun(n - 1) + fibonacciFun(n - 2);
    }

    public static IntStream generateFibonacciSequence() {
        return IntStream.iterate(1, new IntUnaryOperator() {

            private int prev = 0;

            @Override
            public int applyAsInt(int operand) {
                int temp = operand + prev;
                prev = operand;
                return temp;
            }
        });
    }


}
