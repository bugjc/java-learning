package com.bugjc.java.basics.design.pattern.factory.method.factory;

import com.bugjc.java.basics.design.pattern.factory.method.button.Button;
import com.bugjc.java.basics.design.pattern.factory.method.button.WindowsButton;

/**
 * Windows 对话框将产生 Windows 按钮。
 * @author 杨青 2022/11/14
 **/
public class WindowsDialog extends BaseDialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
