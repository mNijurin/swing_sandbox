package com.alcodev.sandbox.forms.tableform;

/**
 * User: mnijurin
 * Date: 8/21/12
 * Time: 12:28 PM
 */
public interface PersonTableFormActionListener {
    void onRowClick(int rowNumber, PersonsTableFormData rowData);

    void onRowRightClick(int rowNumber);
}
