package com.bugjc.java.basics.design.pattern.factory.method;

import com.bugjc.java.basics.design.pattern.factory.method.factory.BaseDialog;
import com.bugjc.java.basics.design.pattern.factory.method.factory.HtmlDialog;
import com.bugjc.java.basics.design.pattern.factory.method.factory.WindowsDialog;

/**
 * 工厂方法测试入口
 * @author 杨青 2022/11/14
 **/
public class Demo {
    private static BaseDialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    static void configure() {
        if ("Windows 10".equals(System.getProperty("os.name"))) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}
