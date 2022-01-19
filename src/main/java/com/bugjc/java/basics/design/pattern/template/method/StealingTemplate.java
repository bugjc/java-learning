package com.bugjc.java.basics.design.pattern.template.method;

import lombok.extern.slf4j.Slf4j;

/**
 * 偷窃模板方法
 * @author aoki
 * @date 2022/1/6
 * **/
@Slf4j
public abstract class StealingTemplate {

    /**
     * 1.选择目标
     * @return 目标
     */
    protected abstract String pickTarget();

    /**
     * 2.迷惑目标
     * @param target
     */
    protected abstract void confuseTarget(String target);

    /**
     * 3.偷东西
     * @param target
     */
    protected abstract void stealTheItem(String target);

    public void steal() {
        var target = pickTarget();
        log.info("目标已被选为{}。", target);
        confuseTarget(target);
        stealTheItem(target);
    }
}
