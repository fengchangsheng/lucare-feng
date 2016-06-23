package com.fcs.demo.base;


/**
 * Created by Lucare.Feng on 2016/4/13.
 */
public class Test {

    private final Person person = new Person(1,"lucare");

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        Long sum = 0l;
        for (long i = 0;i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println((System.currentTimeMillis()-begin));
    }
}
