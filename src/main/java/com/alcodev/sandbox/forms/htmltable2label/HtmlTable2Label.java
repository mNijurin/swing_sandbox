package com.alcodev.sandbox.forms.htmltable2label;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * User: mnijurin
 * Date: 15.10.12
 * Time: 12:24
 */
public class HtmlTable2Label {
    private JPanel contentPanel;
    private JEditorPane jEditorPane;

    private JLabel jLabel;

    private String data;

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setData(String data) {
        this.data = data;
    }

    private void createUIComponents() {
        jLabel = new JLabel();
        jLabel.setOpaque(true);
        jLabel.setBackground(Color.GREEN);
        jLabel.setHorizontalAlignment(JLabel.CENTER);

        jLabel.setMinimumSize(new Dimension(10, 10));
        jLabel.setPreferredSize(new Dimension(5000, 5000));
        jLabel.setMaximumSize(new Dimension(5000, 5000));

        jEditorPane = new JEditorPane();
        jEditorPane.setContentType("text/html");
        jEditorPane.setOpaque(false);

        FormLayout layout = new FormLayout(
                "fill:30dlu:grow",
                "fill:30dlu:grow");

        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();

        CellConstraints constraints = new CellConstraints();

//        builder.add(jLabel, constraints.xy(1, 1));
        builder.add(jEditorPane, constraints.xy(1, 1));

        contentPanel = builder.getPanel();
    }

    public void update() {
//        jLabel.setText(data);
        jEditorPane.setText(data);
    }
}
