package com.alcodev.sandbox.forms.jtablecustomcreate;

import javax.swing.*;

/**
 * User: mnijurin
 * Date: 04.10.12
 * Time: 14:17
 */
public class UsageExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTable custom create");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTableCustomCreate jTableCustomCreate = new JTableCustomCreate();

        frame.add(jTableCustomCreate.getContentPanel());

        frame.setSize(400, 300);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
