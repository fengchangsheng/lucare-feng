package com.fcs.demo.base.equals;

/**
 * Created by Lucare.Feng on 2016/6/23.
 */
public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {

        /**
         * 能够在一定程度上解决问题  但是结果通常不可接受
         * 父子类再加上一个容器就容易出问题   达不到预期的结果
         */
     /*   if (obj == null || obj.getClass() != getClass()) {
            return false;
        }*/


        if (!(obj instanceof Point))
            return false;
        Point p = (Point) obj;
        return p.x == x && p.y == y;
    }
}
