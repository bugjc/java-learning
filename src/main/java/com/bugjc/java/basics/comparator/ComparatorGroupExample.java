package com.bugjc.java.basics.comparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * 分组示例，比较器应用场景
 * @author aoki
 * @date 2019/11/25
 * **/
public class ComparatorGroupExample {

    static class Apple {
        String color;
        int weight;

        Apple(String color, int weight) {
            super();
            this.color = color;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Apple [color=" + color + ", weight=" + weight + "]";
        }
    }

    /**
     * @author puyf
     * @Description:
     * @param data
     * @param c
     *            是否为同一组的判断标准
     * @return
     */
    /**
     * 按条件分组
     * @param data  --原始数据
     * @param c     --是否为同一组的判断标准
     * @param <T>  --比较器类型
     * @return  比较的结果
     */
    public static <T> List<List<T>> divider(Collection<T> data, Comparator<? super T> c) {
        List<List<T>> result = new ArrayList<List<T>>();
        for (T t : data) {
            boolean isSameGroup = false;
            for (List<T> ts : result) {
                if (c.compare(t, ts.get(0)) == 0) {
                    isSameGroup = true;
                    ts.add(t);
                    break;
                }
            }
            if (!isSameGroup) {
                // 创建
                List<T> innerList = new ArrayList<T>();
                result.add(innerList);
                innerList.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        list.add(new Apple("红", 205));
        list.add(new Apple("红", 131));
        list.add(new Apple("绿", 248));
        list.add(new Apple("绿", 153));
        list.add(new Apple("黄", 119));
        list.add(new Apple("黄", 224));
        List<List<Apple>> byColors = divider(list, (o1, o2) -> {
            // 按颜色分组
            return o1.color.compareTo(o2.color);
        });
        System.out.println("按颜色分组" + byColors);
        List<List<Apple>> byWeight = divider(list, (o1, o2) -> {
            // 按重量级
            return (o1.weight / 100 == o2.weight / 100) ? 0 : 1;
        });
        System.out.println("按重量级分组" + byWeight);
    }

}
