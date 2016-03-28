package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class DoubleInfo extends ConstInfo {
    static final int tag = 6;
    double value;

    public DoubleInfo(double d)
    {
        this.value = d;
    }

    public DoubleInfo(DataInputStream in) throws IOException {
        this.value = in.readDouble();
    }

    public int getTag() {
        return 6;
    }

    public int copy(ConstPool src, ConstPool dest, Map<?, ?> map) {
        return dest.addDoubleInfo(this.value);
    }

    public void write(DataOutputStream out) throws IOException {
        out.writeByte(6);
        out.writeDouble(this.value);
    }

    public void print(PrintWriter out) {
        out.print("Double ");
        out.println(this.value);
    }
}
