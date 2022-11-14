package com.bugjc.java.basics.design.pattern.factory.method.factory;

import com.bugjc.java.basics.design.pattern.factory.method.button.Button;

/**
 * 基础工厂类。请注意，“工厂”仅仅是类的一个角色。它应该有一些需要创建不同产品的核心业务逻辑。
 * @author 杨青 2022/11/14
 */
public abstract class BaseDialog {

    public void renderWindow() {
        // ... other code ...

        Button okButton = createButton();
        okButton.render();
    }

    /**
     * 子类将覆盖此方法以创建特定的按钮对象。
     * @return Button
     */
    public abstract Button createButton();
}
