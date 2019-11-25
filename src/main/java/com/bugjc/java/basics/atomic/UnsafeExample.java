package com.bugjc.java.basics.atomic;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 类描述
 * @author aoki
 * @date 2019/11/14
 * **/
@Slf4j
class UnsafeExample {

    private static Location location = new Location(0, 0);

    @Data
    static class Location {
        /**
         * 获得一个UnSafe实例
         */
        private Unsafe unsafe = null;
        //经度
        private int lng;
        //维度
        private int lat;
        //记录经纬度之和
        private int[] location = new int[10];
        private long locationAddr;

        Location(int lng, int lat) {
            this.lng = lng;
            this.lat = lat;
            //初始化对象
            initUnsafe();
        }

        /**
         * 通过 构建 unsafe 实例来获取 Location对象
         */
        void initUnsafe(){
            try {
                if (unsafe != null){
                    return;
                }
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                unsafe = (Unsafe) f.get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        /**
         * 计算 location 数组存储位置
         * @param index
         * @return
         */
        private long locationAddr(int index){
            //计算存储位置：arrayBaseOffset = arrayBaseOffset + index * arrayIndexScale;
            //返回 location 数组中第一个元素的偏移地址
            long arrayBaseOffset = unsafe.arrayBaseOffset(location.getClass());
            //返回数组中一个元素占用的大小
            int arrayIndexScale = unsafe.arrayIndexScale(location.getClass());
            //31减去 arrayIndexScale 从左往右0的个数
            int tShift = 31 - Integer.numberOfLeadingZeros(arrayIndexScale);
            return ((long)index << tShift) + arrayBaseOffset;
        }

        /**
         * 获取 location 数组指定索引下的值
         * @param index
         * @return
         */
        int getLocation(int index){
            return (int)unsafe.getObjectVolatile(location, locationAddr(index));
        }

        /**
         * 从内存地址中添加数据并获取
         * @return
         */
        String getAndAddLocation(int index, int lng, int lat){
            long locationAddr = locationAddr(index);
            log.info("Array address is : {}", locationAddr);
            unsafe.putInt(location, locationAddr, lng + lat);
            return Arrays.toString(location);
        }

        /**
         * CAS 修改 location 数组指定索引下的值
         * @param index         --数组索引位置
         * @param expect        --期望索引位置的值
         * @param update        --修改后的值
         * @return
         */
        boolean compareAndSwapInt(int index, int expect, int update){
            return unsafe.compareAndSwapInt(this, locationAddr(index), expect, update);
        }
    }

    /**
     * 从内存地址中添加数据并获取
     * @param lng
     * @param lat
     * @return
     */
    static String getAndAddLocation(int index, int lng, int lat){
        return location.getAndAddLocation(index, lng, lat);
    }

}
