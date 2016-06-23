package com.fcs.demo.base.equals;

/**
 * Created by Lucare.Feng on 2016/6/23.
 */
public class Teacher extends Parent{

    private int teachYear;

    public Teacher(int age, String name) {
        super(age, name);
    }

    public int getTeachYear() {
        return teachYear;
    }

    public void setTeachYear(int teachYear) {
        this.teachYear = teachYear;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "age=" + getAge() +
                ", name='" + getName() +
                "',teachYear=" + teachYear +
                '}';
    }
}
