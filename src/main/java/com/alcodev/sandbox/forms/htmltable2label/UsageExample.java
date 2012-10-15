package com.alcodev.sandbox.forms.htmltable2label;

import javax.swing.*;
import java.awt.*;

/**
 * User: mnijurin
 * Date: 15.10.12
 * Time: 12:25
 */
public class UsageExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("UsageExample Frame");
        HtmlTable2Label sandboxForm = new HtmlTable2Label();

        sandboxForm.setData(new DataProvider().provideHtmlTable());

        frame.add(sandboxForm.getContentPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(600, 500));
        frame.setVisible(true);

        sandboxForm.update();
    }
}
