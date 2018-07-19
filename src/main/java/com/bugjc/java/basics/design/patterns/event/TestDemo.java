package com.bugjc.java.basics.design.patterns.event;

public class TestDemo {

    DemoSource ds;
    public TestDemo(){
        try{
            ds = new DemoSource();
            //将监听器在事件源对象中登记：
            DemoListenerImpl listener1 = new DemoListenerImpl();
            ds.addDemoListener(listener1);
            ds.addDemoListener(new DemoListener() {

                public void handleEvent(DemoEvent event) {
                    System.out.println("匿名监听器");
                }

            });
            ds.notifyDemoEvent();//触发事件、通知监听器
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new TestDemo();
    }

}
