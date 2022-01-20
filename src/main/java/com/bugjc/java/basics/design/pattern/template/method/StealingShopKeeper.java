package com.bugjc.java.basics.design.pattern.template.method;

import lombok.extern.slf4j.Slf4j;

/**
 * 偷窃店主
 * @author aoki
 * @date 2022/1/6
 * **/
@Slf4j
public class StealingShopKeeper extends StealingTemplate{
    @Override
    protected String pickTarget() {
        return "店主";
    }

    @Override
    protected void confuseTarget(String target) {
        log.info("流着泪走近{}并拥抱他！", target);
    }

    @Override
    protected void stealTheItem(String target) {
        log.info("在密切接触时抓住{}的钱包。", target);
    }
}
