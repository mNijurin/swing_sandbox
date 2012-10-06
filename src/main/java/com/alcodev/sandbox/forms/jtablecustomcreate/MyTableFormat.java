package com.alcodev.sandbox.forms.jtablecustomcreate;

import ca.odell.glazedlists.gui.TableFormat;

/**
 * User: mnijurin
 * Date: 04.10.12
 * Time: 14:23
 */
public class MyTableFormat implements TableFormat<String> {
    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int i) {
        return "asdfadsf";
    }

    @Override
    public Object getColumnValue(String s, int index) {
        if (index == 0) {
            return s;
        } else throw new IllegalStateException("Column count must be = 1");
    }
}
