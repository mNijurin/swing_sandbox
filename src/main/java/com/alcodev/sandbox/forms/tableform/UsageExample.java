package com.alcodev.sandbox.forms.tableform;

import javax.swing.*;
import java.util.Date;
import java.util.Random;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 4:17 PM
 */
public class UsageExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Person's Table");
        final PersonsTableForm form = new PersonsTableForm();
        frame.add(form.getContentPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 400);
        frame.setVisible(true);

        final Random random = new Random();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    PersonsTableFormData data = new PersonsTableFormData();
                    data.setName("Name" + random.nextInt());
                    data.setSurname("Surname" + random.nextInt());
                    data.setBirthday(new Date());
                    form.getData().add(data);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }).start();
    }
}
