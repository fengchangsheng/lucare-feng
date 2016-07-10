package com.fcs.demo.rpc;

/**
 * Created by Lucare.Feng on 2016/7/10.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello "+name;
    }
}
