package com.alcodev.sandbox.forms.tableform;

import ca.odell.glazedlists.*;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.impl.ThreadSafeList;
import ca.odell.glazedlists.swing.EventJXTableModel;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 4:14 PM
 */
public class PersonsTableForm {
    public static final int COULUMNS_COUNT = 3;
    public static final int ROW_HEIGHT = 30;
    public static final int DOUBLE_CLICK = 2;
    private JXTable tableUserData;
    private JPanel contentPanel;
    private EventList<PersonsTableFormData> personsTableData = new ThreadSafeList<PersonsTableFormData>(new BasicEventList<PersonsTableFormData>());

    private PersonTableFormActionListener actionListener;

    public PersonsTableForm() {
        final EventJXTableModel<PersonsTableFormData> model = new EventJXTableModel<PersonsTableFormData>(personsTableData, new PersonTableFormat());
        tableUserData.setModel(model);
        tableUserData.setAutoCreateRowSorter(false);
        tableUserData.setRowSorter(null);
        tableUserData.setSortable(false);

        tableUserData.setRowHeight(ROW_HEIGHT);
        tableUserData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (actionListener != null && mouseEvent.getClickCount() == DOUBLE_CLICK) {
                    JTable target = (JTable) mouseEvent.getSource();
                    Point point = mouseEvent.getPoint();
                    int row = target.rowAtPoint(point);
                    UsageExample.logger.debug("row {} clicked", row);
                    actionListener.onRowClick(model.getElementAt(row));
                }
            }
        });

        personsTableData.addListEventListener(model);
    }

    public void setActionListener(PersonTableFormActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public EventList<PersonsTableFormData> getPersonsTableData() {
        return personsTableData;
    }

    private static class PersonTableFormat implements TableFormat<PersonsTableFormData> {
        @Override
        public int getColumnCount() {
            return COULUMNS_COUNT;
        }

        @Override
        public String getColumnName(int index) {
            switch (index) {
                case 0:
                    return "Имя";
                case 1:
                    return "Фамилия";
                case 2:
                    return "Дата рождения";
                default:
                    throw new IllegalStateException("Column count must be < 3");
            }
        }

        @Override
        public Object getColumnValue(PersonsTableFormData item, int index) {
            switch (index) {
                case 0:
                    return item.getName();
                case 1:
                    return item.getSurname();
                case 2: {
                    SimpleDateFormat formatBirthday = new SimpleDateFormat("dd.MM.yyyy");
                    return formatBirthday.format(item.getBirthday());
                }
                default:
                    throw new IllegalStateException("Column count must be < 3");
            }
        }
    }
}
