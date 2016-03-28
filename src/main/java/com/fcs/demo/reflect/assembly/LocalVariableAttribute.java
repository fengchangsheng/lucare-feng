package com.fcs.demo.reflect.assembly;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class LocalVariableAttribute extends AttributeInfo {
    private static final Logger logger = LoggerFactory.getLogger(LocalVariableAttribute.class);
    public static final String tag = "LocalVariableTable";
    public static final String typeTag = "LocalVariableTypeTable";
    private boolean findThisTag = false;
    private int thisTagIndex = 0;

    public LocalVariableAttribute(ConstPool cp, int n, DataInputStream in) throws IOException {
        super(cp, n, in);
    }

    public String variableName(int i) {
        return getConstPool().getUtf8Info(nameIndex(i));
    }

    public int nameIndex(int i) {
        return ByteArray.readU16bit(this.info, i * 10 + 6);
    }

    public String methodVariableName(int index) {
        try {
            if (!this.findThisTag) {
                for (; ; this.thisTagIndex += 1) {
                    String name = variableName(this.thisTagIndex).trim();
                    if ("this".equals(name)) {
                        this.findThisTag = true;
                        break;
                    }
                }
            }

            return getConstPool().getUtf8Info(nameIndex(this.thisTagIndex + index));
        } catch (Exception e) {
            logger.error("[LocalVariableAttribute] methodVariableName with:", e);
            this.findThisTag = false;
            this.thisTagIndex = 0;
        }
        return null;
    }
}
