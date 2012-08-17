package com.alcodev.sandbox;

import com.alcodev.sandbox.forms.editform.EditForm;
import com.alcodev.sandbox.forms.editform.EditFormActionListener;
import com.alcodev.sandbox.forms.editform.EditFormDataClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Calendar;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 11:08 AM
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Frame");
        final EditForm editForm = new EditForm();
        EditFormDataClass data = new EditFormDataClass();
        data.setName("Василий");
        data.setSurname("Пупкин");
        Calendar birthdayTmp = Calendar.getInstance();
        birthdayTmp.set(2012, 5, 12);
        data.setBirthday(birthdayTmp.getTime());

        editForm.setData(data);
        editForm.setActionListener(new EditFormActionListener() {
            @Override
            public void submitClick() {
                EditFormDataClass result = editForm.getData();
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
