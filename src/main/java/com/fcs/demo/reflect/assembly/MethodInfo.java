package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class MethodInfo {
    ConstPool constPool;
    int accessFlags;
    int name;
    String cachedName;
    int descriptor;
    List<AttributeInfo> attribute;
    public static boolean doPreverify = false;
    public static final String nameInit = "<init>";
    public static final String nameClinit = "<clinit>";

    private MethodInfo(ConstPool cp)
    {
        this.constPool = cp;
        this.attribute = null;
    }

    public MethodInfo(ConstPool cp, String methodname, String desc) {
        this(cp);
        this.accessFlags = 0;
        this.name = cp.addUtf8Info(methodname);
        this.cachedName = methodname;
        this.descriptor = this.constPool.addUtf8Info(desc);
    }

    MethodInfo(ConstPool cp, DataInputStream in) throws IOException {
        this(cp);
        read(in);
    }

    public String toString() {
        return getName() + " " + getDescriptor();
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

    public boolean isMethod() {
        String n = getName();
        return (!n.equals("<init>")) && (!n.equals("<clinit>"));
    }

    public ConstPool getConstPool() {
        return this.constPool;
    }

    public boolean isConstructor() {
        return getName().equals("<init>");
    }

    public boolean isStaticInitializer() {
        return getName().equals("<clinit>");
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    public void setAccessFlags(int acc) {
        this.accessFlags = acc;
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

    public AttributeInfo getAttribute(String name) {
        return AttributeInfo.lookup(this.attribute, name);
    }

    private void read(DataInputStream in) throws IOException {
        this.accessFlags = in.readUnsignedShort();
        this.name = in.readUnsignedShort();
        this.descriptor = in.readUnsignedShort();
        int n = in.readUnsignedShort();
        this.attribute = new ArrayList();
        for (int i = 0; i < n; i++)
            this.attribute.add(AttributeInfo.read(this.constPool, in));
    }

    public CodeAttribute getCodeAttribute() {
        AttributeInfo info = AttributeInfo.lookup(this.attribute, "Code");
        return (CodeAttribute)info;
    }
}
