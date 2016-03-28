package com.fcs.demo.reflect;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Lucare.Feng on 2016/3/28.
 */
public class ClassFile {
//    private static final Logger logger = LoggerFactory.getLogger(ClassFile.class);
//    private int[] interfaces;
//    private List<FieldInfo> fields;
//    private List<MethodInfo> methods;
//
//    private ClassFile(DataInputStream in) throws IOException {
//        this.read(in);
//    }
//
//    private static InputStream openClassfile(String classname, ClassLoader appClassLoader) {
//        String className = classname.replace('.', '/') + ".class";
//        return appClassLoader != null?appClassLoader.getResourceAsStream(className):Thread.currentThread().getContextClassLoader().getResourceAsStream(className);
//    }
//
//    public static ClassFile createClassFile(String classQualifiedName) {
//        return createClassFile(classQualifiedName, (ClassLoader)null);
//    }
//
//    public static ClassFile createClassFile(String classQualifiedName, ClassLoader appClassLoader) {
//        BufferedInputStream is = null;
//
//        try {
//            InputStream is1 = openClassfile(classQualifiedName, appClassLoader);
//            is = new BufferedInputStream(is1);
//            ClassFile e = new ClassFile(new DataInputStream(is));
//            ClassFile var4 = e;
//            return var4;
//        } catch (Exception var14) {
//            logger.error("[ClassFile] createClassFile with:", var14);
//        } finally {
//            if(is != null) {
//                try {
//                    is.close();
//                } catch (Exception var13) {
//                    logger.error("[ClassFile] createClassFile InputStream close with:", var13);
//                }
//            }
//
//        }
//
//        return null;
//    }
//
//    private void read(DataInputStream in) throws IOException {
//        int magic = in.readInt();
//        if(magic != -889275714) {
//            throw new IOException("bad magic number: " + Integer.toHexString(magic));
//        } else {
//            int minor = in.readUnsignedShort();
//            int major = in.readUnsignedShort();
//            ConstPool constPool = new ConstPool(in);
//            int accessFlags = in.readUnsignedShort();
//            int thisClass = in.readUnsignedShort();
//            int superClass = in.readUnsignedShort();
//            int interfaceNum = in.readUnsignedShort();
//            if(interfaceNum > 0) {
//                this.interfaces = new int[interfaceNum];
//
//                for(int cp = 0; cp < interfaceNum; ++cp) {
//                    this.interfaces[cp] = in.readUnsignedShort();
//                }
//            }
//
//            ConstPool var14 = constPool;
//            int fieldNum = in.readUnsignedShort();
//            this.fields = new ArrayList();
//
//            int methodNum;
//            for(methodNum = 0; methodNum < fieldNum; ++methodNum) {
//                this.addField(new FieldInfo(var14, in));
//            }
//
//            methodNum = in.readUnsignedShort();
//            this.methods = new ArrayList();
//
//            for(int i = 0; i < methodNum; ++i) {
//                this.addMethod(new MethodInfo(var14, in));
//            }
//
//        }
//    }
//
//    public final void addMethod(MethodInfo minfo) {
//        this.methods.add(minfo);
//    }
//
//    public final void addField(FieldInfo finfo) {
//        this.fields.add(finfo);
//    }
//
//    public MethodInfo getMethod(String name) {
//        Iterator i$ = this.methods.iterator();
//
//        MethodInfo mi;
//        do {
//            if(!i$.hasNext()) {
//                return null;
//            }
//
//            mi = (MethodInfo)i$.next();
//        } while(!mi.getName().equals(name));
//
//        return mi;
//    }
//
//    public LocalVariableAttribute getMethodLocalVariableAttribute(String methodName) {
//        MethodInfo methodInfo = this.getMethod(methodName);
//        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
//        return codeAttribute == null?null:codeAttribute.getAttribute("LocalVariableTable");
//    }
}
