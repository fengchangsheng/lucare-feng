package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public final class FieldInfo {
    private ConstPool constPool;
    private int accessFlags;
    private int name;
    private String cachedName;
    private int descriptor;
    private List<AttributeInfo> attribute;

    private FieldInfo(ConstPool cp)
    {
        this.constPool = cp;
        this.accessFlags = 0;
        this.attribute = null;
    }

    public FieldInfo(ConstPool cp, String fieldName, String desc) {
        this(cp);
        this.name = cp.addUtf8Info(fieldName);
        this.cachedName = fieldName;
        this.descriptor = cp.addUtf8Info(desc);
    }

    FieldInfo(ConstPool cp, DataInputStream in) throws IOException {
        this(cp);
        read(in);
    }

    public String toString() {
        return getName() + " " + getDescriptor();
    }

    public ConstPool getConstPool() {
        return this.constPool;
    }

    public String getName() {
        if (this.cachedName == null) {
            this.cachedName = this.constPool.getUtf8Info(this.name);
        }
        return this.cachedName;
    }

    public void setName(String newName) {
        this.name = this.constPool.addUtf8Info(newName);
        this.cachedName = newName;
    }

    public String getDescriptor() {
        return this.constPool.getUtf8Info(this.descriptor);
    }

    public void setDescriptor(String desc) {
        if (!desc.equals(getDescriptor()))
            this.descriptor = this.constPool.addUtf8Info(desc);
    }

    public List<AttributeInfo> getAttributes() {
        if (this.attribute == null) {
            this.attribute = new ArrayList();
        }
        return this.attribute;
    }

    private void read(DataInputStream in) throws IOException {
        this.accessFlags = in.readUnsignedShort();
        this.name = in.readUnsignedShort();
        this.descriptor = in.readUnsignedShort();
        int n = in.readUnsignedShort();
        this.attribute = new ArrayList();
        AttributeInfo a = null;
        for (int i = 0; i < n; i++)
            a = AttributeInfo.read(this.constPool, in);
        if (a != null)
            this.attribute.add(a);
    }
}
