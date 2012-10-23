package com.alcodev.sandbox.forms.tableform;

import org.jdesktop.swingx.JXTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 4:17 PM
 */
public class UsageExample {
    public static Logger logger = LoggerFactory.getLogger(UsageExample.class);

    private static int clickedRow;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Person's Table");
        final PersonsTableForm personsTableForm = new PersonsTableForm();
        frame.add(personsTableForm.getContentPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 400);
        frame.setVisible(true);

        personsTableForm.setActionListener(new PersonTableFormActionListener() {
            @Override
            public void onRowClick(int rowNumber, PersonData rowData) {
                if (rowData == null) {
                    clickedRow = rowNumber;
                } else {
                    PersonData result = rowData;
                    logger.debug("click result: name = {}, surname = {}, birthday = {} ", new Object[]{result.getName(), result.getSurname(), result.getBirthday()});
                }
            }

            @Override
            public void onRowRightClick(int rowNumber, JXTable tableUserData, Point mousePoint) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        PersonData data1 = new PersonData();
        data1.setName("Василий");
        data1.setSurname("Головачёв");
        Calendar birthdayTmp1 = Calendar.getInstance();
        birthdayTmp1.set(2012, 5, 12);
        data1.setBirthday(birthdayTmp1.getTime());
        personsTableForm.getPersonsData().add(data1);

        PersonData data2 = new PersonData();
        data2.setName("Макс");
        data2.setSurname("фрай");
        Calendar birthdayTmp2 = Calendar.getInstance();
        birthdayTmp2.set(2013, 5, 12);
        data2.setBirthday(birthdayTmp2.getTime());
        logger.debug("adding data");
        personsTableForm.getPersonsData().add(data2);

        PersonData data3 = new PersonData();
        data3.setName("Алекс");
        data3.setSurname("Пушкин");
        Calendar birthdayTmp3 = Calendar.getInstance();
        birthdayTmp3.set(2014, 5, 12);
        data3.setBirthday(birthdayTmp3.getTime());
        personsTableForm.getPersonsData().add(data3);
    }
}
