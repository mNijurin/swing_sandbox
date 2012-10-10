package com.alcodev.sandbox.forms.jxlayerusage;

import javax.swing.*;

/**
 * User: mnijurin
 * Date: 8/21/12
 * Time: 5:41 PM
 */
public class UsageExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("UsageExample Frame");
        JGoodiesSandboxForm sandboxForm = new JGoodiesSandboxForm();

        frame.add(sandboxForm.getContentPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
