package com.bugjc.java.basics.design.pattern.factory.method.factory;

import com.bugjc.java.basics.design.pattern.factory.method.button.Button;
import com.bugjc.java.basics.design.pattern.factory.method.button.HtmlButton;

/**
 * HTML 对话框将产生 HTML 按钮。
 * @author 杨青 2022/11/14
 **/
public class HtmlDialog extends BaseDialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
