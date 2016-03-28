package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class NameAndTypeInfo extends ConstInfo {
    static final int tag = 12;
    int memberName;
    int typeDescriptor;

    public NameAndTypeInfo(int name, int type)
    {
        this.memberName = name;
        this.typeDescriptor = type;
    }

    public NameAndTypeInfo(DataInputStream in) throws IOException {
        this.memberName = in.readUnsignedShort();
        this.typeDescriptor = in.readUnsignedShort();
    }

    boolean hashCheck(int a, int b) {
        return (a == this.memberName) && (b == this.typeDescriptor);
    }

    public int getTag() {
        return 12;
    }

    public void renameClass(ConstPool cp, String oldName, String newName) {
    }

    public void renameClass(ConstPool cp, Map<?, ?> map) {
    }

    public void write(DataOutputStream out) throws IOException {
        out.writeByte(12);
        out.writeShort(this.memberName);
        out.writeShort(this.typeDescriptor);
    }

    public void print(PrintWriter out) {
        out.print("NameAndType #");
        out.print(this.memberName);
        out.print(", type #");
        out.println(this.typeDescriptor);
    }

    public int copy(ConstPool src, ConstPool dest, Map<?, ?> classnames) {
        return 0;
    }
}
