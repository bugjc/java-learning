package com.bugjc.java.basics.lambda;

/**
 * 自定义Lambda接口步骤：
 * 1. 定义一个函数式接口，接口增加注解@FunctionalInterface，防止接口增加其他方法导致故障；
 * 2. 接口定义方法；
 * 3. 调用方法传入数据和Lambda表达式，操作数据。
 * @author aoki
 * @date 2021/10/13
 * **/
public class CustomInterfacesTest {
    public static void main(String[] args) {
        //无返回值无参数
        LambdaNoReturnNoParameter lambdaNoReturnNoParameter = ()->{
            System.out.println("无返回值无参数");
        };
        lambdaNoReturnNoParameter.test();

        //无返回值单个参数
        LambdaNoReturnSingleParameter lambdaNoReturnSingleParameter = (int a)->{
            System.out.println("无返回值单个参数: "+a);
        };
        lambdaNoReturnSingleParameter.test(2);

        //无返回值多个参数
        LambdaNoReturnMultipleParameter lambdaNoReturnMultipleParameter = (int a,int b)->{
            System.out.println("无返回值多个参数: "+a+b);
        };
        lambdaNoReturnMultipleParameter.test(1, 2);

        //有返回值无参数
        LambdaHaveReturnNoParameter lambdaHaveReturnNoParameter = ()->{
            System.out.println("有返回值无参数："+6);
            return 6;
        };
        int test = lambdaHaveReturnNoParameter.test();
        System.out.println("有返回值无参数："+test);

        //有返回值单个参数
        LambdaHaveReturnSingleParameter lambdaHaveReturnSingleParameter = (int a)->{
            System.out.println("有返回值单个参数："+a);
            return a;
        };
        int test2 = lambdaHaveReturnSingleParameter.test(7);
        System.out.println("有返回值单个参数："+test2);

        //有返回值多个参数
        LambdaHaveReturnMultipleParameter lambdaHaveReturnMultipleParameter=(int a,int b)->{
            System.out.println("有返回值多个参数："+a+b);
            return a+b;
        };
        int test3 = lambdaHaveReturnMultipleParameter.test(8, 9);
        System.out.println("有返回值多个参数："+test3);

        //简洁写法:参数类型，大括号，return ，在此省略
        LambdaHaveReturnMultipleParameter lambdaHaveReturnMultipleParameter2=(x,y)->x+y;
        lambdaHaveReturnMultipleParameter2.test(8, 9);
        System.out.println("有返回值多个参数的简洁写法: "+test3);
    }
}
