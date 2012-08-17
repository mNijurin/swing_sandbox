package com.alcodev.sandbox;

import com.alcodev.sandbox.forms.mainform.MainForm;
import com.alcodev.sandbox.forms.mainform.MainFormActionListener;
import com.alcodev.sandbox.forms.mainform.MainFormDataClass;
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
        final MainForm mainForm = new MainForm();
        MainFormDataClass data = new MainFormDataClass();
        data.setName("Василий");
        data.setSurname("Пупкин");
        Calendar birthdayTmp = Calendar.getInstance();
        birthdayTmp.set(2012, 5, 12);
        data.setBirthday(birthdayTmp.getTime());

        mainForm.setData(data);
        mainForm.setActionListener(new MainFormActionListener() {
            @Override
            public void submitClick() {
                MainFormDataClass result = mainForm.getData();
                logger.debug("Edit result: {}", result);
            }
        });
        frame.add(mainForm.getContentPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
