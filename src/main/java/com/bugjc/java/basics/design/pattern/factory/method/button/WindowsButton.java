package com.bugjc.java.basics.design.pattern.factory.method.button;

import javax.swing.*;
import java.awt.*;

/**
 * Windows 按钮产品实现
 * @author 杨青 2022/11/14
 **/
public class WindowsButton implements Button {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JButton button;

    @Override
    public void render() {
        System.out.println("<JButton>Test Button</JButton>");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Hello World!");
        label.setOpaque(true);
        label.setBackground(new Color(235, 233, 126));
        label.setFont(new Font("Dialog", Font.BOLD, 44));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.getContentPane().add(panel);
        panel.add(label);
        onClick();
        panel.add(button);

        frame.setSize(320, 200);
        frame.setVisible(true);
        onClick();
    }

    @Override
    public void onClick() {
        button = new JButton("Exit");
        button.addActionListener(e -> {
            System.out.println("Click! Button says - 'Hello World!'");
            frame.setVisible(false);
            System.exit(0);
        });
    }
}
