package com.fcs.demo.reflect.assembly;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class ClassFile {
    private static final Logger logger = LoggerFactory.getLogger(ClassFile.class);
    private int[] interfaces;
    private List<FieldInfo> fields;
    private List<MethodInfo> methods;

    private ClassFile(DataInputStream in)
            throws IOException
    {
        read(in);
    }

    private static InputStream openClassfile(String classname, ClassLoader appClassLoader) {
        String className = classname.replace('.', '/') + ".class";
        return appClassLoader != null ? appClassLoader.getResourceAsStream(className) : Thread.currentThread().getContextClassLoader().getResourceAsStream(className);
    }

    public static ClassFile createClassFile(String classQualifiedName)
    {
        return createClassFile(classQualifiedName, null);
    }

    public static ClassFile createClassFile(String classQualifiedName, ClassLoader appClassLoader) {
        InputStream is = null;
        try {
            is = openClassfile(classQualifiedName, appClassLoader);
            is = new BufferedInputStream(is);
            ClassFile classFile = new ClassFile(new DataInputStream(is));
            return classFile;
        } catch (Exception e) {
            logger.error("[ClassFile] createClassFile with:", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    logger.error("[ClassFile] createClassFile InputStream close with:", e);
                }
            }
        }
        return null;
    }

    private void read(DataInputStream in) throws IOException {
        int magic = in.readInt();
        if (magic != -889275714) {
            throw new IOException("bad magic number: " + Integer.toHexString(magic));
        }
        int minor = in.readUnsignedShort();
        int major = in.readUnsignedShort();
        ConstPool constPool = new ConstPool(in);
        int accessFlags = in.readUnsignedShort();
        int thisClass = in.readUnsignedShort();
        int superClass = in.readUnsignedShort();

        int interfaceNum = in.readUnsignedShort();
        if (interfaceNum > 0) {
            this.interfaces = new int[interfaceNum];
            for (int i = 0; i < interfaceNum; i++) {
                this.interfaces[i] = in.readUnsignedShort();
            }
        }
        ConstPool cp = constPool;

        int fieldNum = in.readUnsignedShort();
        this.fields = new ArrayList();
        for (int i = 0; i < fieldNum; i++) {
            addField(new FieldInfo(cp, in));
        }
        int methodNum = in.readUnsignedShort();
        this.methods = new ArrayList();
        for (int i = 0; i < methodNum; i++)
            addMethod(new MethodInfo(cp, in));
    }

    public final void addMethod(MethodInfo minfo) {
        this.methods.add(minfo);
    }

    public final void addField(FieldInfo finfo) {
        this.fields.add(finfo);
    }

    public MethodInfo getMethod(String name) {
        for (MethodInfo mi : this.methods) {
            if (mi.getName().equals(name)) {
                return mi;
            }
        }
        return null;
    }

    public LocalVariableAttribute getMethodLocalVariableAttribute(String methodName) {
        MethodInfo methodInfo = getMethod(methodName);
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        return codeAttribute == null ? null : codeAttribute.getAttribute("LocalVariableTable");
    }
}
