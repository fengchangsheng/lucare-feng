package com.fcs.demo.base.equals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Lucare.Feng on 2016/6/23.
 */
public class TestEquals {

    public static void main(String[] args) {
        Parent parent = new Parent(30, "张0");

        Parent parent1 = new Parent(31, "张1");
        Parent parent2 = new Parent(32, "张2");
        Parent parent3 = new Parent(33, "张3");
        Parent parent4 = new Parent(34, "张4");
        Parent teacher = new Teacher(33, "张3");
        Set<Parent> set = new HashSet<Parent>();
        set.add(parent);
        set.add(parent1);
        set.add(parent2);
        set.add(parent3);
        set.add(parent4);
        set.add(teacher);
        Iterator<Parent> iterator = set.iterator();
        while (iterator.hasNext()) {
            Parent par = iterator.next();
            System.out.println(par);
        }
    }
}
