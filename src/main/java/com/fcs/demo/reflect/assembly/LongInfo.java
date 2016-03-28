package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class LongInfo extends ConstInfo {
    static final int tag = 5;
    long value;

    public LongInfo(long l)
    {
        this.value = l;
    }

    public LongInfo(DataInputStream in) throws IOException {
        this.value = in.readLong();
    }

    public int getTag() {
        return 5;
    }

    public int copy(ConstPool src, ConstPool dest, Map<?, ?> map) {
        return dest.addLongInfo(this.value);
    }

    public void write(DataOutputStream out) throws IOException {
        out.writeByte(5);
        out.writeLong(this.value);
    }

    public void print(PrintWriter out) {
        out.print("Long ");
        out.println(this.value);
    }
}
