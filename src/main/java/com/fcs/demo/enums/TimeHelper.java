package com.fcs.demo.enums;

/**
 * Created by Lucare.Feng on 2016/4/2.
 */
public class TimeHelper {
    public void getTimes(TimeClassify timeClassify){

    }

    public static void main(String[] args) {
        String name = TimeClassify.DAY.name();
        System.out.println(name);
        System.out.println(TimeClassify.HOUR);
    }
}
