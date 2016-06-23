package com.fcs.demo.base.builder;

/**
 * Created by Lucare.Feng on 2016/6/21.
 * Builder Pattern
 * 如果类的构造器或者静态工厂中具有多个参数，设计这种类时，Builder模式是种不错的选择，
 * 特别是当大多数参数都是可选的时候(静态内部类可以实现Builder<T>接口的build方法)
 */
public class Animal {

    private int age;
    private String color;
    private String name;
    private int height;
    private int weight;

    public static class Builder{
        private int age;
        private String name;

        private String color;
        private int height;
        private int weight;

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder color(String val){
            color = val;
            return this;
        }

        public Builder height(int val){
            height = val;
            return this;
        }

        public Builder weight(int val){
            weight = val;
            return this;
        }

        public Animal build(){
            return new Animal(this);
        }

    }

    private Animal(Builder builder){
        age = builder.age;
        color = builder.color;
        name = builder.name;
        height = builder.height;
        weight = builder.weight;
    }


    public static void main(String[] args) {
        Animal animal = new Animal.Builder("kitty",3).color("yello").height(20).build();
    }
}
