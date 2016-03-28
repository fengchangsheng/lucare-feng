package com.fcs.demo.reflect;

/**
 * Created by Lucare.Feng on 2016/3/28.
 */
public class Hello {

    public static void main(String[] args) {
        try {
            Class personClass = Class.forName("com.fcs.demo.reflect.Person");
//            Person person  = (Person) personClass.newInstance();
//            person.sayPerson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
