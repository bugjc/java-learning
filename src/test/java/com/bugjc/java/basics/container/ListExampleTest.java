package com.bugjc.java.basics.container;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import BeforeEach;
import Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
class ListExampleTest {

    private List<ListExample.Location> locations1;
    private List<ListExample.Location> locations2;

    @BeforeEach
    void setUp() {
        locations1 = new ArrayList<>();
        locations1.add(new ListExample.Location(1,1));
        locations1.add(new ListExample.Location(2,2));

        locations2 = new ArrayList<>();
        locations2.add(new ListExample.Location(2,2));
        locations2.add(new ListExample.Location(3,3));
    }

    @Test
    void join() {
        log.info("将一个集合全部加入另一个集合");
        log.info("两集合的并集：{}", ListExample.join(locations1, locations2).toString());
    }

    @Test
    void deduplicate() {
        log.info("先将 locations1 中与 locations2 重复的去掉，之后将 locations2 的元素全部添加进去");
        log.info("两个集合去重复并集：{}", ListExample.deduplicate(locations1, locations2).toString());
    }

    @Test
    void beMixed() {
        log.info("求 locations1 和 locations2 中都有的元素");
        log.info("两个集合的交集：{}", ListExample.beMixed(locations1, locations2).toString());
    }

    @Test
    void differenceSet() {
        log.info("求 locations1 中有的但是 locations2 中没有的元素");
        log.info("两个集合的差集：{}", ListExample.differenceSet(locations1, locations2).toString());
    }

    @Test
    void listToMap(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            users.add(new User("answer", new Random().nextInt(100)));
        }
        System.out.println(JSON.toJSONString(users));

        System.out.println();

        // 如果 key 重复, 则根据 冲突方法 ·(key1, key2) -> key2· 判断. 解释: key1 key2 冲突时 取 key2
        Map<String, User> map = users.stream().collect(Collectors.toMap(User::getName, Function.identity(), (key1, key2) -> key2));
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    void deduplication(){

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            users.add(new User("answer", new Random().nextInt(100)));
        }
        System.out.println(JSON.toJSONString(users));

        System.out.println();

        // 根据name去重
        List<User> unique = users.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getName))), ArrayList::new)
        );
        System.out.println(JSON.toJSONString(unique));

        // 根据name,age两个属性去重
        unique = users.stream().collect(
                Collectors. collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getName() + ";" + o.getAge()))), ArrayList::new)
        );
        System.out.println(JSON.toJSONString(unique));

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private Long id;
        private String name;
        private Integer age;

        public User(String name) {
            this.name = name;
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}