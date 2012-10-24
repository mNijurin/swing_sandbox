package com.alcodev.sandbox.forms.jtableudjustcells;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

/**
 * User: mnijurin
 * Date: 10/23/12
 * Time: 4:58 PM
 */
public class RunnableTableAdjustExample {
    private static JTable mainTable;
    private static Random random = new Random();

    private static List<Data> data;

    private static class Data {
        String name;
        String surname;
        String address;
        String someLongString;

        private Data(String name, String surname, String address, String someLongString) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.someLongString = someLongString;
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        data = stubProvider();

        final JFrame frame = new JFrame("table adjust example");
        frame.setContentPane(createPanel());
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        update();
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 3000, 3000);
    }

    private static JPanel createPanel() {
        JPanel jPanel = new JPanel();
        mainTable = new JTable(2, 3);
        mainTable.setModel(new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return data.size();
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Data dataItem = data.get(rowIndex);
                if (columnIndex == 0) {
                    return dataItem.name;
                }
                if (columnIndex == 1) {
                    return dataItem.surname;
                }
                if (columnIndex == 2) {
                    return dataItem.address;
                }
                if (columnIndex == 3) {
                    return dataItem.someLongString;
                }
                throw new IllegalStateException();
            }
        });
        mainTable.setGridColor(Color.black);
        mainTable.setShowHorizontalLines(false);
        mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        final TableCellRenderer defaultRenderer = mainTable.getTableHeader().getDefaultRenderer();
        mainTable.getTableHeader().setDefaultRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int row, int column) {
                JLabel parent = (JLabel) defaultRenderer.getTableCellRendererComponent(jTable, o, b, b1, row, column);
                if (column == 0) {
                    parent.setText("name");
                } else if (column == 1) {
                    parent.setText("surname");
                } else if (column == 2) {
                    parent.setText("address");
                } else if (column == 3) {
                    parent.setText("some long string");
                }
                return parent;
            }
        });

        JScrollPane jScrollPane = new JScrollPane(mainTable);
        jPanel.add(jScrollPane);

        return jPanel;
    }

    private static void update() {
        System.out.println("updating");
        data = stubProvider();

        adjustJTableRowSizes(mainTable);
        for (int i = 0; i < mainTable.getColumnCount(); i++) {
            adjustColumnSizes(mainTable, i, 2);
        }
    }

    private static void adjustJTableRowSizes(JTable jTable) {
        for (int row = 0; row < jTable.getRowCount(); row++) {
            int maxHeight = 0;
            for (int column = 0; column < jTable.getColumnCount(); column++) {
                TableCellRenderer cellRenderer = jTable.getCellRenderer(row, column);
                Object valueAt = jTable.getValueAt(row, column);
                Component tableCellRendererComponent = cellRenderer.getTableCellRendererComponent(jTable, valueAt, false, false, row, column);
                int heightPreferable = tableCellRendererComponent.getPreferredSize().height;
                maxHeight = Math.max(heightPreferable, maxHeight);
            }
            jTable.setRowHeight(row, maxHeight);
        }

    }

    public static void adjustColumnSizes(JTable table, int column, int margin) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn col = colModel.getColumn(column);
        int width;

        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        JLabel comp = (JLabel) renderer.getTableCellRendererComponent(
                table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;

        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, column);
            comp = (JLabel) renderer.getTableCellRendererComponent(
                    table, table.getValueAt(r, column), false, false, r, column);
            int currentWidth = comp.getPreferredSize().width;
            width = Math.max(width, currentWidth);
        }

        width += 2 * margin;

        col.setPreferredWidth(width);
    }

    private static List<Data> stubProvider() {
        List<Data> data = new ArrayList<Data>();
        for (int i = 0; i < 4; i++) {
            data.add(new Data(
                    "<html>" +
                            "<div style='font-size: 15px'>Jason</div>" +
                            "<div style='font-size: 15px'>" + random.nextInt() + "</div>" +
                            "</html>",
                    "Statham " + random.nextInt(),
                    random.nextInt() + " " + random.nextInt(),
                    "<html>" +
                            "<div style='font-face: Arial'>LongLongLongLongLongLongLongLong string</div>" +
                            "</html>"));
        }
        return data;
    }
}
