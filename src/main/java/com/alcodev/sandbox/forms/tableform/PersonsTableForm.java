package com.alcodev.sandbox.forms.tableform;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.impl.ThreadSafeList;
import ca.odell.glazedlists.swing.EventTableModel;

import javax.swing.*;
import java.text.SimpleDateFormat;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 4:14 PM
 */
public class PersonsTableForm {
    public static final int COULUMNS_COUNT = 3;
    public static final int ROW_HEIGHT = 30;
    private JTable tableUserData;
    private JPanel contentPanel;
    private EventList<PersonsTableFormData> data = new ThreadSafeList<PersonsTableFormData>(new BasicEventList<PersonsTableFormData>());

    public PersonsTableForm() {
        EventTableModel<PersonsTableFormData> model = new EventTableModel<PersonsTableFormData>(data, new PersonTableFormat());
        tableUserData.setModel(model);
        tableUserData.setRowHeight(ROW_HEIGHT);
        data.addListEventListener(model);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public EventList<PersonsTableFormData> getData() {
        return data;
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
                    SimpleDateFormat formatBirthday = new SimpleDateFormat("yyyy-mm-dd");
                    return formatBirthday.format(item.getBirthday());
                }
                default:
                    throw new IllegalStateException("Column count must be < 3");
            }
        }
    }
}
