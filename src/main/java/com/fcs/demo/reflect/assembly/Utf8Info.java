package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class Utf8Info extends ConstInfo {
    static final int tag = 1;
    String string;
    int index;

    public Utf8Info(String utf8, int i)
    {
        this.string = utf8;
        this.index = i;
    }

    public Utf8Info(DataInputStream in, int i) throws IOException {
        this.string = in.readUTF();
        this.index = i;
    }

    public int getTag() {
        return 1;
    }

    public int copy(ConstPool src, ConstPool dest, Map<?, ?> map) {
        return dest.addUtf8Info(this.string);
    }

    public void write(DataOutputStream out) throws IOException {
        out.writeByte(1);
        out.writeUTF(this.string);
    }

    public void print(PrintWriter out) {
        out.print("UTF8 \"");
        out.print(this.string);
        out.println("\"");
    }
}
