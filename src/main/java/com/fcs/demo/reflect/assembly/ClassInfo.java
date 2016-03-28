package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class ClassInfo extends ConstInfo {
    static final int tag = 7;
    int name;
    int index;

    public ClassInfo(int className, int i)
    {
        this.name = className;
        this.index = i;
    }

    public ClassInfo(DataInputStream in, int i) throws IOException {
        this.name = in.readUnsignedShort();
        this.index = i;
    }

    public int getTag() {
        return 7;
    }

    public String getClassName(ConstPool cp) {
        return cp.getUtf8Info(this.name);
    }

    public void write(DataOutputStream out) throws IOException {
        out.writeByte(7);
        out.writeShort(this.name);
    }

    public void print(PrintWriter out) {
        out.print("Class #");
        out.println(this.name);
    }

    void makeHashtable(ConstPool cp) {
    }

    public int copy(ConstPool src, ConstPool dest, Map<?, ?> classnames) {
        return 0;
    }
}
