package com.fcs.demo.base;

import org.apache.commons.lang.StringUtils;

import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * Created by Lucare.Feng on 2016/4/13.
 */
public class Test {

    public static void main(String[] args) {
        boolean is = StringUtils.isEmpty("   ");
        System.out.println(is);
    }
}
