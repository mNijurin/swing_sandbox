package com.alcodev.sandbox.forms.tableform;

import ca.odell.glazedlists.EventList;

import java.util.Date;

/**
 * User: mnijurin
 * Date: 8/17/12
 * Time: 4:25 PM
 */
public class PersonData {
    private String name;
    private String surname;
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + birthday.toString();
    }
}
