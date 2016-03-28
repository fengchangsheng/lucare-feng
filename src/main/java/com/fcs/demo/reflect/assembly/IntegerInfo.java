package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class IntegerInfo extends ConstInfo {
    static final int tag = 3;
    int value;

    public IntegerInfo(int i)
    {
        this.value = i;
    }

    public IntegerInfo(DataInputStream in) throws IOException {
        this.value = in.readInt();
    }

    public int getTag() {
        return 3;
    }

    public int copy(ConstPool src, ConstPool dest, Map<?, ?> map) {
        return dest.addIntegerInfo(this.value);
    }

    public void write(DataOutputStream out) throws IOException {
        out.writeByte(3);
        out.writeInt(this.value);
    }

    public void print(PrintWriter out) {
        out.print("Integer ");
        out.println(this.value);
    }
}
