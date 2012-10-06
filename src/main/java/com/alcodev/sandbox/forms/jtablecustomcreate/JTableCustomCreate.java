package com.alcodev.sandbox.forms.jtablecustomcreate;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.impl.ThreadSafeList;
import ca.odell.glazedlists.swing.EventTableModel;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * User: mnijurin
 * Date: 04.10.12
 * Time: 14:16
 */
public class JTableCustomCreate {
    private JPanel contentPanel;


    private void createUIComponents() {

        EventList<String> string = new ThreadSafeList<String>(new BasicEventList<String>());

        EventTableModel<String> model = new EventTableModel<String>(string, new MyTableFormat());


//        contentPanel = new JPanel();

        JTable jTable = new JTable();
        jTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setPreferredSize(new Dimension(450, 32000));

//        contentPanel.add(scrollPane);
        fillStrings(string);

        FormLayout layout = new FormLayout(
                "3dlu, left:p:grow, 3dlu,",
                "3dlu, top:100dlu:grow, 3dlu,");

        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();

        CellConstraints constraints = new CellConstraints();

        builder.add(scrollPane, constraints.xy(2, 2));

        contentPanel = builder.getPanel();
    }

    private void fillStrings(EventList<String> string) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            string.add(String.valueOf(random.nextInt()));
        }
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
