package com.fcs.demo.reflect.assembly;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class ConstInfoPadding extends ConstInfo {
    public int getTag()
    {
        return 0;
    }

    public int copy(ConstPool src, ConstPool dest, Map<?, ?> map) {
        return dest.addConstInfoPadding();
    }

    public void write(DataOutputStream out) throws IOException {
    }

    public void print(PrintWriter out) {
        out.println("padding");
    }
}
