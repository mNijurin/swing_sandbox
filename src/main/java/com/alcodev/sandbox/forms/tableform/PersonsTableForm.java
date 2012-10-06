package com.alcodev.sandbox.forms.tableform;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.impl.ThreadSafeList;
import ca.odell.glazedlists.swing.EventJXTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
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
    public static final int ONE_CLICK = 1;
    static private Logger logger = LoggerFactory.getLogger(PersonsTableForm.class);
    private JTable tableUserData;
    private JPanel contentPanel;
    private EventList<PersonsTableFormData> personsTableData = new ThreadSafeList<PersonsTableFormData>(new BasicEventList<PersonsTableFormData>());

    private PersonTableFormActionListener actionListener;

    public PersonsTableForm() {
        final EventJXTableModel<PersonsTableFormData> model = new EventJXTableModel<PersonsTableFormData>(personsTableData, new PersonTableFormat());
        tableUserData.setModel(model);
        tableUserData.setAutoCreateRowSorter(false);
        tableUserData.setRowSorter(null);
//        tableUserData.setSortable(false);

        tableUserData.setRowHeight(ROW_HEIGHT);
        tableUserData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (actionListener != null) {
                    int row = ((JTable) mouseEvent.getSource()).rowAtPoint(mouseEvent.getPoint());
                    if (mouseEvent.getButton() == MouseEvent.BUTTON1 && row == -1) {
                        logger.debug("row {} clicked", row);
                        actionListener.onRowClick(row, null);
                        tableUserData.clearSelection();
                    } else if (mouseEvent.getButton() == MouseEvent.BUTTON1 && mouseEvent.getClickCount() == ONE_CLICK) {
                        logger.debug("row {} clicked", row);
                        actionListener.onRowClick(row, null);
                    } else if (mouseEvent.getButton() == MouseEvent.BUTTON1 && mouseEvent.getClickCount() == DOUBLE_CLICK) {
                        logger.debug("row {} clicked", row);
                        actionListener.onRowClick(row, model.getElementAt(row));
                    } else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                        logger.debug("row {} right clicked", row);
//                        actionListener.onRowRightClick(row, tableUserData, mouseEvent.getPoint());
                    }
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
