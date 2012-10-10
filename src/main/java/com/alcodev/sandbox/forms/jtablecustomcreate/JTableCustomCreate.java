package com.alcodev.sandbox.forms.jtablecustomcreate;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.impl.ThreadSafeList;
import ca.odell.glazedlists.swing.EventTableModel;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        final JTable jTable = new JTable();
        jTable.setModel(model);
        jTable.setDefaultRenderer(Object.class, new MyRenderer());

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setPreferredSize(new Dimension(450, 32000));

        fillStrings(string);

        JButton jButton = new JButton("test add column");
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                jTable.getColumnModel().addColumn(new TableColumn(jTable.getColumnCount()));
            }
        });

        FormLayout layout = new FormLayout(
                "3dlu, left:p:grow, 3dlu,",
                "3dlu, top:100dlu:grow, 3dlu, top:30dlu:g");

        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();

        CellConstraints constraints = new CellConstraints();

        builder.add(scrollPane, constraints.xy(2, 2));
        builder.add(jButton, constraints.xy(2, 4));

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

    public class MyTableFormat implements TableFormat<String> {
        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public String getColumnName(int i) {
            return "asdfadsf";
        }

        @Override
        public Object getColumnValue(String s, int index) {
            if (index == 0) {
                return s;
            }
            if (index == 1) return "1";
            if (index == 2) return "2";
            if (index == 3) return "3";
            return "4";
        }
    }

    private class MyRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable jTable, Object o,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel parent = (JLabel) super.getTableCellRendererComponent(jTable, o, isSelected, hasFocus, row, column);

            if (column == 0) {
                parent.setFont(parent.getFont().deriveFont(Font.BOLD));
            }
            int i = row % 2;
            if (i == 0) {
                parent.setBackground(Color.decode("#EBFFE9"));
            } else parent.setBackground(Color.white);
            return parent;
        }
    }
}
