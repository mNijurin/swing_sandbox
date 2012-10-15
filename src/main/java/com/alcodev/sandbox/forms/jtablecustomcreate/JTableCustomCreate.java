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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: mnijurin
 * Date: 04.10.12
 * Time: 14:16
 */
public class JTableCustomCreate {
    private JPanel contentPanel;
    private JTable jTable;
    final EventList<JTableData> tableData = new ThreadSafeList<JTableData>(new BasicEventList<JTableData>());

    public JTableCustomCreate() {
        createUIComponents();


        EventTableModel<JTableData> model = new EventTableModel<JTableData>(tableData, new MyTableFormat());

        jTable.setModel(model);
        jTable.setDefaultRenderer(Object.class, new MyRenderer());
        jTable.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());
        fillStrings(tableData);
    }

    private void createUIComponents() {


        this.jTable = new JTable();

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setPreferredSize(new Dimension(450, 32000));


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

    private void fillStrings(EventList<JTableData> tableData) {
        for (int i = 0; i < 50; i++) {
            JTableData data = new JTableData();
            data.setBoolValue(false);
            data.setString("qwer" + i);
            tableData.add(data);
        }
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public class MyTableFormat implements TableFormat<JTableData> {
        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int i) {
            return "asdfadsf";
        }

        @Override
        public Object getColumnValue(JTableData data, int index) {
            if (index == 0) {
                return data.getString();
            }
            if (index == 1) return data.getBoolValue();
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
            if (row % 2 == 0 && !isSelected) {
                parent.setBackground(Color.decode("#EBFFE9"));
            } else if (!isSelected) {
                setBackground(Color.white);
            }
            return parent;
        }
    }

    public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

        CheckBoxRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, final int row, final int column) {
            if (row % 2 == 0 && !isSelected) {
                setBackground(Color.decode("#EBFFE9"));
            } else if (!isSelected) {
                setBackground(Color.white);
            }
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            }
            setSelected((value != null && (Boolean) value));
            return this;
        }
    }
}
