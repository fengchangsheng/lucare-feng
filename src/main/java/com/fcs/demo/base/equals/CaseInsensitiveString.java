package com.fcs.demo.base.equals;

/**
 * Created by Lucare.Feng on 2016/6/21.
 * 对称性
 */
public class CaseInsensitiveString {

    private String s;

    public CaseInsensitiveString(String s) {
        if (s == null)
            throw new NullPointerException();
        this.s = s;
    }

    @Override
    public boolean equals(Object obj) {
//        if (obj instanceof CaseInsensitiveString)
//            return s.equalsIgnoreCase(((CaseInsensitiveString) obj).s);
//        if (obj instanceof String)
//            return s.equalsIgnoreCase((String) obj);

        return obj instanceof CaseInsensitiveString && ((CaseInsensitiveString) obj).s.equalsIgnoreCase(s);

//        return false;
    }

    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        System.out.println(cis.equals(s));//true
        System.out.println(s.equals(cis));//false  违反了对称性
        System.out.println(cis.equals(null));

    }
}
