package com.alcodev.sandbox.forms.personeditform;

import com.jgoodies.binding.beans.Model;

import java.util.Date;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 12:06 PM
 */
public class PersonEditFormData extends Model {
    private String name;
    private String surname;
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        firePropertyChange("name", oldName, name);
        System.out.println("name after change = " + this.surname);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        String oldSurname = this.surname;
        this.surname = surname;
        firePropertyChange("surname", oldSurname, surname);
        System.out.println("surname after change = " + this.surname);
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        Date oldBirthday = this.birthday;
        this.birthday = birthday;
        firePropertyChange("birthday", oldBirthday, birthday);
        System.out.println("birthday after change = " + this.birthday.toString());
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + birthday.toString();
    }
}
