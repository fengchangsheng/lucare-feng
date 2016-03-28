package com.fcs.demo.reflect.assembly;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Lucare.Feng on 2016/3/27.
 */
public class FieldrefInfo extends MemberrefInfo{
    static final int tag = 9;

    public FieldrefInfo(int cindex, int ntindex)
    {
        super(cindex, ntindex);
    }

    public FieldrefInfo(DataInputStream in) throws IOException {
        super(in);
    }

    public int getTag() {
        return 9;
    }

    public String getTagName() {
        return "Field";
    }

    protected int copy2(ConstPool dest, int cindex, int ntindex) {
        return dest.addFieldrefInfo(cindex, ntindex);
    }
}
