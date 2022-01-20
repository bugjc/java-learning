package com.bugjc.java.basics.bit;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 位运算符使用
 *
 * @author qingyang
 * @date 2018/9/19 16:28
 */
@Slf4j
public class BitExample {
    public static void main(String[] args) {


        log.info("{}", 1 >> 1);
        log.info("{}", 1 << 4);

        //1、将5左移2位(右移同理)：<<
        //整数默认32位
        //5的二进制表示为：0000 0000 0000 0000 0000 0000 0000 0101
        //左移2位，低位补0
        //结果：0000 0000 0000 0000 0000 0000 0000 0100
        //换算成10进制为20
        log.info("5 <<（左移） 2 位 = {}", 5 << 2);

        //2、无符号右移: >>>
        //-5的二进制表示为：1111 1111 1111 1111 1111 1111 1111 1111
        //右移3位，用0补位
        //结果：0001 1111 1111 1111 1111 1111 1111 1111
        log.info("5 >>>（无符号右移） 3 位 = {}", 5 >>> 3);

        //3、位与（&）
        //5转换为二进制：0000 0000 0000 0000 0000 0000 0000 0101
        //3转换为二进制：0000 0000 0000 0000 0000 0000 0000 0011
        //结果：1
        //1转换为二进制：0000 0000 0000 0000 0000 0000 0000 0001
        log.info("5 &（位与） 3 = {}", 5 & 3);

        //4、位或（|）
        //5转换为二进制：0000 0000 0000 0000 0000 0000 0000 0101
        //3转换为二进制：0000 0000 0000 0000 0000 0000 0000 0011
        //结果：7
        //7转换位二进制：0000 0000 0000 0000 0000 0000 0000 0111
        log.info("5 |（位或） 3 = {}", 5 | 3);

        //5、位异或（^）
        //5转换为二进制：0000 0000 0000 0000 0000 0000 0000 0101
        //3转换为二进制：0000 0000 0000 0000 0000 0000 0000 0011
        //结果：6
        //6转换位二进制：0000 0000 0000 0000 0000 0000 0000 0110
        log.info("5 ^（位异） 3 = {}", 5 ^ 3);

        //6、位非（～）
        //5转换为二进制：0000 0000 0000 0000 0000 0000 0000 0101
        //结果：-6
        //-6转换位二进制：1111 1111 1111 1111 1111 1111 1111 1010
        log.info("5 的 ~（位非）值 = {}", ~5);
        int a = -15, b = 15;

        // -15 = [1111 0001] 补1，右移二位，最高位由符号位填充将得到 [1111 1100] 即 -4
        int right = a >> 2;
        log.info("{} 右移 2 位：{}", a, right);

        // 15 = [0000 1111] 补0，右移二位，最高位由符号位填充将得到 [0000 0011] 即 3
        right = b >> 2;
        log.info("{} 右移 2 位：{}", b, right);


        BitExample bitMain = new BitExample();
        boolean flag = bitMain.isEven(999);
        log.info("判断奇偶数：{}", flag);

        int[] value = bitMain.crossoverValue(a, b);
        log.info("{}, {} 交换后的值是：{}, {}", a, b, value[0], value[1]);

        int num = bitMain.opposite(a);
        log.info("取反后的值是：{}", num);

        num = bitMain.abs(num);
        log.info("绝对值：{}", num);

        int max = 100;
        int[] primes = bitMain.primeNumber(max);
        log.info("筛选素数：{}", Arrays.toString(primes));

        int e = 2;
        e |= 1 << 1;
        log.info("指定第 {} 位，置 1", e);
    }

    /**
     * 判断奇偶（true：偶数，false：奇数）
     *
     * @param num
     * @return
     */
    public boolean isEven(int num) {
        return (num & 1) == 0;
    }

    /**
     * 交换两数值
     *
     * @param a
     * @param b
     * @return [a, b]
     */
    public int[] crossoverValue(int a, int b) {
        a ^= b;
        b ^= a;
        a ^= b;
        return new int[]{a, b};
    }

    /**
     * 取反
     *
     * @param num
     * @return
     */
    public int opposite(int num) {
        return ~num + 1;
    }

    /**
     * 绝对值
     * 解：正数右移31位为0，负数右移31位为-1
     *
     * @param num
     * @return
     */
    public int abs(int num) {
        int i = num >> 31;
        return (num ^ i) - i;
    }

    /**
     * 筛选素数
     *
     * @param max
     * @return
     */
    public int[] primeNumber(int max) {
        boolean[] flags = new boolean[max];
        int[] primes = new int[max / 3 + 1];
        int pi = 0;

        for (int m = 2; m < max; m++) {
            if (!flags[m]) {
                primes[pi++] = m;
                for (int n = m; n < max; n += m) {
                    flags[n] = true;
                }
            }
        }
        return primes;
    }
}
