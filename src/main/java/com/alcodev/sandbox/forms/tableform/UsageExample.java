package com.alcodev.sandbox.forms.tableform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Date;
import java.util.Random;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 4:17 PM
 */
public class UsageExample {
    public static Logger logger = LoggerFactory.getLogger(UsageExample.class);

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
            public void onRowClick(PersonsTableFormData rowData) {
                PersonsTableFormData result = rowData;
                logger.debug("click result: name = {}, surname = {}, birthday = {} ", new Object[]{result.getName(), result.getSurname(), result.getBirthday()});
            }
        });
        final Random random = new Random();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    PersonsTableFormData data = new PersonsTableFormData();
                    data.setName("Name" + random.nextInt());
                    data.setSurname("Surname" + random.nextInt());
                    data.setBirthday(new Date());
                    personsTableForm.getPersonsTableData().add(data);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }).start();
    }
}
