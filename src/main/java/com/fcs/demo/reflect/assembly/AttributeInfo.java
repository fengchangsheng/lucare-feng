package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class AttributeInfo {
    protected ConstPool constPool;
    int name;
    byte[] info;

    public AttributeInfo(ConstPool cp, int attrname, byte[] attrinfo)
    {
        this.constPool = cp;
        this.name = attrname;
        this.info = attrinfo;
    }

    public AttributeInfo(ConstPool cp, String attrname) {
        this(cp, attrname, (byte[])null);
    }

    public AttributeInfo(ConstPool cp, String attrname, byte[] attrinfo) {
        this(cp, cp.addUtf8Info(attrname), attrinfo);
    }

    public AttributeInfo(ConstPool cp, int n, DataInputStream in) throws IOException {
        this.constPool = cp;
        this.name = n;
        int len = in.readInt();
        this.info = new byte[len];
        if (len > 0)
            in.readFully(this.info);
    }

    protected static void doRead(ConstPool cp, int n, DataInputStream in) throws IOException {
        int len = in.readInt();
        byte[] info = new byte[len];
        if (len > 0)
            in.readFully(info);
    }

    public static AttributeInfo read(ConstPool cp, DataInputStream in) throws IOException {
        int name = in.readUnsignedShort();
        String nameStr = cp.getUtf8Info(name);
        if (nameStr.charAt(0) < 'L') {
            if (nameStr.equals("Code"))
                return new CodeAttribute(cp, name, in);
            doRead(cp, name, in);
        } else {
            if (nameStr.equals("LocalVariableTable")) {
                return new LocalVariableAttribute(cp, name, in);
            }
            doRead(cp, name, in);
        }

        return null;
    }

    public String getName() {
        return this.constPool.getUtf8Info(this.name);
    }

    public ConstPool getConstPool() {
        return this.constPool;
    }

    public int length() {
        return this.info.length + 6;
    }

    public static int getLength(List<AttributeInfo> list) {
        int size = 0;
        int n = list.size();
        for (int i = 0; i < n; i++) {
            AttributeInfo attr = (AttributeInfo)list.get(i);
            size += attr.length();
        }

        return size;
    }

    public static AttributeInfo lookup(List<AttributeInfo> list, String name) {
        if (list == null) {
            return null;
        }
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            AttributeInfo ai = (AttributeInfo)iterator.next();
            if ((ai != null) &&
                    (ai.getName().equals(name))) {
                return ai;
            }
        }
        return null;
    }
}
