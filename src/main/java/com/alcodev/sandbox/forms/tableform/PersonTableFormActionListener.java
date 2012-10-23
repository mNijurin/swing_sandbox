package com.alcodev.sandbox.forms.tableform;

import org.jdesktop.swingx.JXTable;

import java.awt.*;

/**
 * User: mnijurin
 * Date: 8/21/12
 * Time: 12:28 PM
 */
public interface PersonTableFormActionListener {
    void onRowClick(int rowNumber, PersonData rowData);

    void onRowRightClick(int rowNumber, JXTable tableUserData, Point mousePoint);
}
