package com.fcs.demo.annotation;

/**
 * Created by Lucare.Feng on 2016/3/28.
 */
public @interface FruitColor {
    public enum Color{
        BLUE,RED,GREEN
    };

    Color fruitColor() default Color.GREEN;
}
