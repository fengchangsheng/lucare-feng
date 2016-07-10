package com.fcs.demo.rpc;

/**
 * Created by Lucare.Feng on 2016/7/10.
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception{
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service,1234);
    }
}
