package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class StringInfo extends ConstInfo {
    static final int tag = 8;
    int string;

    public StringInfo(int str)
    {
        this.string = str;
    }

    public StringInfo(DataInputStream in) throws IOException {
        this.string = in.readUnsignedShort();
    }

    public int getTag() {
        return 8;
    }

    public int copy(ConstPool src, ConstPool dest, Map<?, ?> map) {
        return dest.addStringInfo(src.getUtf8Info(this.string));
    }

    public void write(DataOutputStream out) throws IOException {
        out.writeByte(8);
        out.writeShort(this.string);
    }

    public void print(PrintWriter out) {
        out.print("String #");
        out.println(this.string);
    }
}
