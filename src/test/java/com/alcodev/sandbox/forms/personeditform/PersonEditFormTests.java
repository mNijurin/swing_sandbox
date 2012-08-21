package com.alcodev.sandbox.forms.personeditform;

import junit.framework.Assert;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.uispec4j.TextBox;
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
    private Mockery mockery = new Mockery();

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

    public void testReverseBinding(){
        String expectedName = "Name" + random.nextInt();
        TextBox textFieldName = getMainWindow().getTextBox("textFieldName");
        textFieldName.setText(expectedName);
        textFieldName.focusLost();

        String expectedSurname = "Surname" + random.nextInt();
        TextBox textFieldSurname = getMainWindow().getTextBox("textFieldSurname");
        textFieldSurname.setText(expectedSurname);
        textFieldSurname.focusLost();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        TextBox textFieldBirthday = getMainWindow().getTextBox("formattedTextFieldBirthday");
        Date expectedBirthday = new Date();
        String dateText = simpleDateFormat.format(expectedBirthday);
        System.out.println("dateText = " + dateText);
        textFieldBirthday.setText(dateText, true);
        System.out.println("dateText from text box = " + textFieldBirthday.getText());

        textFieldBirthday.focusLost();

        PersonEditFormData data = form.getData();
        System.out.println("dateText from user data = " + data.getBirthday());

        Assert.assertEquals(expectedName, data.getName());
        Assert.assertEquals(expectedSurname, data.getSurname());
        Assert.assertEquals(expectedBirthday, data.getBirthday());
    }

    public void testActionListener(){
        final PersonEditFormActionListener actionListener = mockery.mock(PersonEditFormActionListener.class);

        form.setActionListener(actionListener);

        mockery.checking(new Expectations(){{
            oneOf(actionListener).submitClick();
        }});

        getMainWindow().getButton().click();

        mockery.assertIsSatisfied();
    }
}
