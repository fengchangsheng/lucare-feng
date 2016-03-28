package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class CodeAttribute extends AttributeInfo{
    public static final String tag = "Code";
    private int maxStack;
    private int maxLocals;
    private List<AttributeInfo> attributes;

    CodeAttribute(ConstPool cp, int name_id, DataInputStream in)
            throws IOException
    {
        super(cp, name_id, (byte[])null);

        int attr_len = in.readInt();

        this.maxStack = in.readUnsignedShort();
        this.maxLocals = in.readUnsignedShort();

        int code_len = in.readInt();
        this.info = new byte[code_len];
        in.readFully(this.info);

        shiftExceptionTable(cp, in);
        this.attributes = new ArrayList();
        int num = in.readUnsignedShort();
        for (int i = 0; i < num; i++)
            this.attributes.add(AttributeInfo.read(cp, in));
    }

    public LocalVariableAttribute getAttribute(String name) {
        return (LocalVariableAttribute)AttributeInfo.lookup(this.attributes, name);
    }

    public static void shiftExceptionTable(ConstPool cp, DataInputStream in) throws IOException {
        int length = in.readUnsignedShort();
        int type;
        for (int i = 0; i < length; i++) {
            int start = in.readUnsignedShort();

            int end = in.readUnsignedShort();

            int handle = in.readUnsignedShort();

            type = in.readUnsignedShort();
        }
    }
}
