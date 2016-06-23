package com.fcs.demo.base.equals;

/**
 * Created by Lucare.Feng on 2016/6/23.
 */
public class Parent {

    private int age;
    private String name;


    public Parent(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj){
            return true;
        }
        if (!(obj instanceof Parent)){
            return false;
        }

//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }

        Parent other = (Parent) obj;
        System.out.println(this.getClass().getName());
        System.out.println(this.name+ "  " +other.name);
        if (this.name == null ? other.name != null : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
