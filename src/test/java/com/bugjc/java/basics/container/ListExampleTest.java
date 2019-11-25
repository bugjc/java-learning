package com.bugjc.java.basics.container;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
}