package com.alcodev.sandbox;

import com.alcodev.sandbox.forms.personeditform.PersonEditForm;
import com.alcodev.sandbox.forms.personeditform.PersonEditFormActionListener;
import com.alcodev.sandbox.forms.personeditform.PersonEditFormData;
import com.alcodev.sandbox.forms.tableform.PersonTableFormActionListener;
import com.alcodev.sandbox.forms.tableform.PersonsTableForm;
import com.alcodev.sandbox.forms.tableform.PersonsTableFormData;
import org.jdesktop.swingx.JXTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * User: mnijurin
 * Date: 8/24/12
 * Time: 10:35 AM
 */
public class Launcher {
    private static int clickedRow;
    static private Logger logger = LoggerFactory.getLogger(Launcher.class);
    static final JFrame frameEdit = new JFrame("Edit data");
    static PersonEditFormData rowDataClickResult = new PersonEditFormData();
    static final PersonEditForm personEditForm = new PersonEditForm();

    public static void main(String[] args) {

        final JFrame tableFrame = new JFrame("Person's Table");
        final PersonsTableForm personsTableForm = new PersonsTableForm();

        tableFrame.setJMenuBar(createMainMenu(tableFrame, personsTableForm));

        tableFrame.add(personsTableForm.getContentPanel());
        tableFrame.pack();
        tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableFrame.setLocationRelativeTo(null);
        tableFrame.setSize(600, 400);
        tableFrame.setVisible(true);

        personsTableForm.setActionListener(new PersonTableFormActionListener() {
            @Override
            public void onRowClick(int rowNumber, PersonsTableFormData rowData) {
                if (rowData == null) {
                    clickedRow = rowNumber;
                } else {
                    clickedRow = rowNumber;
                    editSelectedRow(rowData);
                }
            }

            @Override
            public void onRowRightClick(int rowNumber, JXTable tableUserData, Point mousePoint) {
                clickedRow = rowNumber;
//                todo: show context menu hear - edit if row>-1, add if row=-1
                if (rowNumber > -1) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem menuItem = new JMenuItem("A popup menu item");
                    popup.add(menuItem);
                    popup.show(tableUserData, mousePoint.x, mousePoint.y);
                }
            }
        });

        stubDataProvider(personsTableForm);
        setupPersonEditForm(personsTableForm);
    }

    private static void setupPersonEditForm(final PersonsTableForm personsTableForm) {
        personEditForm.setActionListener(new PersonEditFormActionListener() {
            @Override
            public void submitClick() {
                PersonsTableFormData submitClickResult = new PersonsTableFormData();
                submitClickResult.setName(personEditForm.getData().getName());
                submitClickResult.setSurname(personEditForm.getData().getSurname());
                submitClickResult.setBirthday(personEditForm.getData().getBirthday());
                logger.debug("Edit result: {}", submitClickResult.toString());

                personsTableForm.getPersonsTableData().set(clickedRow, submitClickResult);
                frameEdit.setVisible(false);
            }
        });
        frameEdit.add(personEditForm.getContentPanel());
        frameEdit.pack();
        frameEdit.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameEdit.setLocationRelativeTo(null);
        frameEdit.setSize(320, 240);
    }

    private static void editSelectedRow(PersonsTableFormData rowData) {
        rowDataClickResult.setName(rowData.getName());
        rowDataClickResult.setSurname(rowData.getSurname());
        rowDataClickResult.setBirthday(rowData.getBirthday());

        logger.debug("click result: name = {}, surname = {}, birthday = {} ", new Object[]{rowDataClickResult.getName(), rowDataClickResult.getSurname(), rowDataClickResult.getBirthday()});

        personEditForm.setData(rowDataClickResult);

        frameEdit.setVisible(true);
    }

    private static JMenuBar createMainMenu(final JFrame frame, final PersonsTableForm personsTableForm) {
        JMenuBar mainMenuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem fileMenuItemExit = new JMenuItem("Exit");
        fileMenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        });
        fileMenu.add(fileMenuItemExit);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem editMenuItemAdd = new JMenuItem("Add");
        editMenu.add(editMenuItemAdd);
        editMenu.add(new JSeparator());

        JMenuItem editMenuItemEdit = new JMenuItem("Edit");
        editMenuItemEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (clickedRow > -1) {
                    editSelectedRow(personsTableForm.getPersonsTableData().get(clickedRow));
                }
            }
        });
        editMenu.add(editMenuItemEdit);

        mainMenuBar.add(fileMenu);
        mainMenuBar.add(editMenu);
        return mainMenuBar;
    }

    private static void stubDataProvider(PersonsTableForm personsTableForm) {
        PersonsTableFormData data1 = new PersonsTableFormData();
        data1.setName("Василий");
        data1.setSurname("Головачёв");
        Calendar birthdayTmp1 = Calendar.getInstance();
        birthdayTmp1.set(2012, Calendar.APRIL, 12);
        data1.setBirthday(birthdayTmp1.getTime());
        personsTableForm.getPersonsTableData().add(data1);

        PersonsTableFormData data2 = new PersonsTableFormData();
        data2.setName("Макс");
        data2.setSurname("Фрай");
        Calendar birthdayTmp2 = Calendar.getInstance();
        birthdayTmp2.set(2013, Calendar.APRIL, 12);
        data2.setBirthday(birthdayTmp2.getTime());
        personsTableForm.getPersonsTableData().add(data2);

        PersonsTableFormData data3 = new PersonsTableFormData();
        data3.setName("Алекс");
        data3.setSurname("Пушкин");
        Calendar birthdayTmp3 = Calendar.getInstance();
        birthdayTmp3.set(2014, Calendar.APRIL, 12);
        data3.setBirthday(birthdayTmp3.getTime());
        personsTableForm.getPersonsTableData().add(data3);
    }
}
