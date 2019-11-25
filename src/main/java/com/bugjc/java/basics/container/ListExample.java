package com.bugjc.java.basics.container;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 *  list常用方法
 * @author aoki
 * @date 2019/11/12
 * **/
public class ListExample {

    /**
     * 位置对象
     */
    @Data
    @AllArgsConstructor
    public static class Location {
        //经度坐标
        private int lng;
        //纬度坐标
        private int lat;

        @Override
        public boolean equals(Object obj) {
            if (this == obj){
                return true;
            }

            if (!(obj instanceof Location)){
                return false;
            }

            Location object = (Location) obj;
            return object.getLng() == this.getLng() && object.getLat() == this.getLat();
        }

        @Override
        public int hashCode() {
            return (this.getLng()+this.getLat());
        }

        @Override
        public String toString(){
            return JSON.toJSONString(this);
        }
    }

    /**
     * 两个集合的并集,例如：[1,2,3] 和 [3,4,5] 的并集是：[1,2,3,3,4,5]
     * @param locations1
     * @param locations2
     * @return
     */
    public static List<Location> join(List<Location> locations1, List<Location> locations2){
        locations1.addAll(locations2);
        return locations1;
    }

    /**
     * 两个集合去重复并集
     * @param locations1
     * @param locations2
     * @return
     */
    public static List<Location> deduplicate(List<Location> locations1, List<Location> locations2){
        locations2.removeAll(locations1);
        locations1.addAll(locations2);
        return locations1;
    }

    /**
     * 两个集合的交集
     * @param locations1
     * @param locations2
     * @return
     */
    public static List<Location> beMixed(List<Location> locations1, List<Location> locations2){
        locations1.retainAll(locations2);
        return locations1;
    }

    /**
     * 两个集合的差集
     * @param locations1
     * @param locations2
     * @return
     */
    public static List<Location> differenceSet(List<Location> locations1, List<Location> locations2){
        locations1.removeAll(locations2);
        return locations1;
    }
}
