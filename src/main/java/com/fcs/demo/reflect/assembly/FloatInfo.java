package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class FloatInfo extends ConstInfo {
    static final int tag = 4;
    float value;

    public FloatInfo(float f)
    {
        this.value = f;
    }

    public FloatInfo(DataInputStream in) throws IOException {
        this.value = in.readFloat();
    }

    public int getTag() {
        return 4;
    }

    public int copy(ConstPool src, ConstPool dest, Map<?, ?> map) {
        return dest.addFloatInfo(this.value);
    }

    public void write(DataOutputStream out) throws IOException {
        out.writeByte(4);
        out.writeFloat(this.value);
    }

    public void print(PrintWriter out) {
        out.print("Float ");
        out.println(this.value);
    }
}
