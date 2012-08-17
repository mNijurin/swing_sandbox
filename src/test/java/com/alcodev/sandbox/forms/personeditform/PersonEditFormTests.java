package com.alcodev.sandbox.forms.personeditform;

import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 6:11 PM
 */
public class PersonEditFormTests extends UISpecTestCase {
    private Random random = new Random();
    private PersonEditForm form = new PersonEditForm();

    @Override
    protected void setUp() throws Exception {
        setAdapter(new UISpecAdapter() {
            @Override
            public Window getMainWindow() {
                JFrame frame = new JFrame();
                frame.add(form.getContentPanel());
                return new Window(frame);
            }
        });
    }

    public void testForwardBinding() {
        String expectedSurname = "Surname" + random.nextInt();
        Date expectedBirthday = new Date();
        String expectedName = "Name" + random.nextInt();

        PersonEditFormData data = new PersonEditFormData();
        data.setName(expectedName);
        data.setSurname(expectedSurname);
        data.setBirthday(expectedBirthday);

        form.setData(data);

        getMainWindow().getTextBox("textFieldName").textEquals(expectedName).check();
        getMainWindow().getTextBox("textFieldSurname").textEquals(expectedSurname).check();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        getMainWindow().getTextBox("formattedTextFieldBirthday").textEquals(simpleDateFormat.format(expectedBirthday)).check();
    }
}
