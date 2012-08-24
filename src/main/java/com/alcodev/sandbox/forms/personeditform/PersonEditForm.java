package com.alcodev.sandbox.forms.personeditform;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.beans.BeanAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 11:52 AM
 */
public class PersonEditForm {
    private JPanel contentPanel;
    private JTextField textFieldSurname;
    private JTextField textFieldName;
    private JButton buttonSubmit;
    private JFormattedTextField formattedTextFieldBirthday;


    private BeanAdapter<PersonEditFormData> adapter;
    private PersonEditFormActionListener actionListener;

    public PersonEditForm() {
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionListener != null) {
                    actionListener.submitClick();
                }
            }
        });
    }

    public void setActionListener(PersonEditFormActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public PersonEditFormData getData() {
        return adapter.getBean();
    }

    public void setData(PersonEditFormData data) {
        adapter.setBean(data);
    }

    private void createUIComponents() {
        adapter = new BeanAdapter<PersonEditFormData>(new PersonEditFormData(), true);
        textFieldName = BasicComponentFactory.createTextField(adapter.getValueModel("name"));
        textFieldSurname = BasicComponentFactory.createTextField(adapter.getValueModel("surname"));
        formattedTextFieldBirthday = BasicComponentFactory.createFormattedTextField(adapter.getValueModel("birthday"), new SimpleDateFormat("dd.MM.yyyy"));
    }
}
