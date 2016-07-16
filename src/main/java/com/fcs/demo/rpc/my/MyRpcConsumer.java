package com.fcs.demo.rpc.my;

/**
 * Created by Lucare.Feng on 2016/7/13.
 */
public class MyRpcConsumer {

    public static void main(String[] args) {
        MyServiceFactory.getService(com.fcs.demo.rpc.my.TwoService.class).sayTwo();
        MyServiceFactory.getService(com.fcs.demo.rpc.my.OneService.class).sayOne();
    }
}
