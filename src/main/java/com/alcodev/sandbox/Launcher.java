package com.alcodev.sandbox;

import com.alcodev.sandbox.forms.personeditform.PersonEditForm;
import com.alcodev.sandbox.forms.personeditform.PersonEditFormActionListener;
import com.alcodev.sandbox.forms.personeditform.PersonEditFormData;
import com.alcodev.sandbox.forms.tableform.PersonTableFormActionListener;
import com.alcodev.sandbox.forms.tableform.PersonsTableForm;
import com.alcodev.sandbox.forms.tableform.PersonsTableFormData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
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

    public static void main(String[] args) {

        final JFrame frame = new JFrame("Person's Table");
        final PersonsTableForm personsTableForm = new PersonsTableForm();

        frame.setJMenuBar(createMainMenu(frame, personsTableForm));

        frame.add(personsTableForm.getContentPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 400);
        frame.setVisible(true);

        personsTableForm.setActionListener(new PersonTableFormActionListener() {
            @Override
            public void onRowClick(int rowNumber, PersonsTableFormData rowData) {
                if (rowData == null) {
                    clickedRow = rowNumber;
                } else {
                    clickedRow = rowNumber;
                    editSelectedRow(rowData, personsTableForm);
                }
            }
        });

        stubDataProvider(personsTableForm);
    }

    private static void editSelectedRow(PersonsTableFormData rowData, final PersonsTableForm personsTableForm) {
        PersonEditFormData result = new PersonEditFormData();
        result.setName(rowData.getName());
        result.setSurname(rowData.getSurname());
        result.setBirthday(rowData.getBirthday());

        logger.debug("click result: name = {}, surname = {}, birthday = {} ", new Object[]{result.getName(), result.getSurname(), result.getBirthday()});
        final JFrame frameEdit = new JFrame("Edit data");

        final PersonEditForm personEditForm = new PersonEditForm();
        personEditForm.setData(result);
        personEditForm.setActionListener(new PersonEditFormActionListener() {
            @Override
            public void submitClick() {
                PersonsTableFormData result = new PersonsTableFormData();
                result.setName(personEditForm.getData().getName());
                result.setSurname(personEditForm.getData().getSurname());
                result.setBirthday(personEditForm.getData().getBirthday());
                logger.debug("Edit result: {}", result);

                personsTableForm.getPersonsTableData().set(clickedRow, result);
                frameEdit.setVisible(false);
            }
        });

        frameEdit.add(personEditForm.getContentPanel());
        frameEdit.pack();
        frameEdit.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameEdit.setLocationRelativeTo(null);
        frameEdit.setSize(320, 240);
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
                    editSelectedRow(personsTableForm.getPersonsTableData().get(clickedRow), personsTableForm);
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
        birthdayTmp1.set(2012, 5, 12);
        data1.setBirthday(birthdayTmp1.getTime());
        personsTableForm.getPersonsTableData().add(data1);

        PersonsTableFormData data2 = new PersonsTableFormData();
        data2.setName("Макс");
        data2.setSurname("Фрай");
        Calendar birthdayTmp2 = Calendar.getInstance();
        birthdayTmp2.set(2013, 5, 12);
        data2.setBirthday(birthdayTmp2.getTime());
        personsTableForm.getPersonsTableData().add(data2);

        PersonsTableFormData data3 = new PersonsTableFormData();
        data3.setName("Алекс");
        data3.setSurname("Пушкин");
        Calendar birthdayTmp3 = Calendar.getInstance();
        birthdayTmp3.set(2014, 5, 12);
        data3.setBirthday(birthdayTmp3.getTime());
        personsTableForm.getPersonsTableData().add(data3);
    }
}
