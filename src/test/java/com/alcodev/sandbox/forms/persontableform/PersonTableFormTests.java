package com.alcodev.sandbox.forms.persontableform;

import com.alcodev.sandbox.forms.tableform.PersonsTableForm;
import com.alcodev.sandbox.forms.tableform.PersonsTableFormData;
import org.uispec4j.Table;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.UISpecTestCase;
import org.uispec4j.Window;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * User: mnijurin
 * Date: 8/21/12
 * Time: 11:52 AM
 */
public class PersonTableFormTests extends UISpecTestCase {
    private Random random = new Random();
    private PersonsTableForm form = new PersonsTableForm();

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

        PersonsTableFormData data = new PersonsTableFormData();
        data.setName(expectedName);
        data.setSurname(expectedSurname);
        SimpleDateFormat birthdayFormatter = new SimpleDateFormat("dd.MM.yyyy");
        data.setBirthday(expectedBirthday);

        form.getPersonsTableData().add(data);

        Table table = getMainWindow().getTable();

        assertTrue(table.contentEquals(new String[][]{
                {expectedName, expectedSurname, birthdayFormatter.format(expectedBirthday)}
        }));
    }
}
