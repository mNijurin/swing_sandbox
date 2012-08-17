package com.alcodev.sandbox.forms.editform;

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
public class EditForm {
    private JPanel contentPanel;
    private JTextField textFieldSurname;
    private JTextField textFieldName;
    private JButton buttonSubmit;
    private JFormattedTextField formattedTextFieldBirthday;


    private BeanAdapter<EditFormDataClass> adapter;
    private EditFormActionListener actionListener;

    public EditForm() {
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionListener != null) {
                    actionListener.submitClick();
                }
            }
        });
    }

    public void setActionListener(EditFormActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public EditFormDataClass getData() {
        return adapter.getBean();
    }

    public void setData(EditFormDataClass data) {
        adapter.setBean(data);
    }

    private void createUIComponents() {
        adapter = new BeanAdapter<EditFormDataClass>(new EditFormDataClass());
        textFieldName = BasicComponentFactory.createTextField(adapter.getValueModel("name"));
        textFieldSurname = BasicComponentFactory.createTextField(adapter.getValueModel("surname"));
        formattedTextFieldBirthday = BasicComponentFactory.createFormattedTextField(adapter.getValueModel("birthday"), new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"));
    }
}
