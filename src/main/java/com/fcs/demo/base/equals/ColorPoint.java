package com.fcs.demo.base.equals;

import java.awt.*;

/**
 * Created by Lucare.Feng on 2016/6/23.
 */
public class ColorPoint extends Point {

    private final Color color;

    public ColorPoint(int x, int y,Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {

        /**
         * 不重写的话颜色信息将被忽略
         */

        /**
         * 牺牲了对称性
         */
        /*
        if (!(obj instanceof ColorPoint))
            return false;
       */

        /**
         * 提供了对称性  却失去了传递性
         */
        if (!(obj instanceof Point))
            return false;
        if (!(obj instanceof ColorPoint))
            return obj.equals(this);

        return super.equals(obj) && ((ColorPoint) obj).color == color;

        /**
         * 解决办法--复合优于继承
         */

    }
}
