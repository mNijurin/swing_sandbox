package com.alcodev.sandbox.forms.mainform;

import com.jgoodies.binding.beans.Model;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 12:06 PM
 */
public class MainFormDataClass extends Model{
    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        firePropertyChange("name", oldName, name);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
