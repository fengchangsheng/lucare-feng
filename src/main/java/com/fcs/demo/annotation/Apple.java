package com.fcs.demo.annotation;

/**
 * Created by Lucare.Feng on 2016/3/28.
 */
public class Apple {
    @FruitName
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.BLUE)
    private String appleColor;


}
