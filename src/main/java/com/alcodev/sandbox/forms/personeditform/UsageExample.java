package com.alcodev.sandbox.forms.personeditform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Calendar;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 11:08 AM
 */
public class UsageExample {
    private static Logger logger = LoggerFactory.getLogger(UsageExample.class);

    public static void main(String[] args) {
        JFrame frame = new JFrame("UsageExample Frame");
        final PersonEditForm editForm = new PersonEditForm();
        PersonEditFormData data = new PersonEditFormData();
        data.setName("Василий");
        data.setSurname("Пупкин");
        Calendar birthdayTmp = Calendar.getInstance();
        birthdayTmp.set(2012, 5, 12);
        data.setBirthday(birthdayTmp.getTime());

        editForm.setData(data);
        editForm.setActionListener(new PersonEditFormActionListener() {
            @Override
            public void submitClick() {
                PersonEditFormData result = editForm.getData();
                logger.debug("Edit result: {}", result);
            }
        });
        frame.add(editForm.getContentPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
