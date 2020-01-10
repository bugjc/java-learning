package com.bugjc.java.basics.design.pattern.delegate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件委托示例
 * @author aoki
 * @date 2020/1/6
 * **/
@Slf4j
public class App {
    public static void main(String[] args) throws InterruptedException {
        //1.创建一个被委托人(事件代理对象)
        EventProxy eventProxy = new EventProxy("李四");

        //2.创建一个委托人
        Client client = new Client("张山");
        //2.1 委托人“张山” 委托 “李四” 8点钟通知他看电视
        eventProxy.addEvent(client, "watchTV");

        //3.创建一个委托人
        Client client1 = new Client("王五");
        //3.2 委托人“张山” 委托 “李四” 8点钟通知他玩游戏
        eventProxy.addEvent(client1, "playGame");

        //4. 早上8点通知
        eventProxy.invokeAll();
    }


    @Slf4j
    @Data
    public static class Client {
        //委托人姓名
        private String name;

        public Client(String name){
            this.name = name;
        }

        /**
         * 行为
         */
        public void watchTV(){
            log.info("8点通知我（{}）看电视", name);
        }

        /**
         * 行为
         */
        public void playGame(){
            log.info("8点通知我（{}）打电视", name);
        }
    }
}
