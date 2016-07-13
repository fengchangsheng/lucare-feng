package com.fcs.demo.base.equals;

import java.awt.*;

/**
 * Created by Lucare.Feng on 2016/6/23.
 */
public class TestPoint {
    public static void main(String[] args) {
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1, 2, Color.red);
        System.out.println(p.equals(cp));
        System.out.println(cp.equals(p));
    }
}
