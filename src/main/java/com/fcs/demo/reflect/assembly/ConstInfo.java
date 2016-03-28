package com.fcs.demo.reflect.assembly;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public abstract class ConstInfo {
    public abstract int getTag();

    public String getClassName(ConstPool cp)
    {
        return null;
    }

    public void renameClass(ConstPool cp, String oldName, String newName) {
    }

    public void renameClass(ConstPool cp, Map<?, ?> classnames) {
    }

    public abstract int copy(ConstPool paramConstPool1, ConstPool paramConstPool2, Map<?, ?> paramMap);

    public abstract void write(DataOutputStream paramDataOutputStream) throws IOException;

    public abstract void print(PrintWriter paramPrintWriter);

    void makeHashtable(ConstPool cp) {
    }

    boolean hashCheck(int a, int b) {
        return false;
    }

    public String toString() {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PrintWriter out = new PrintWriter(bout);
        print(out);
        return bout.toString();
    }
}
