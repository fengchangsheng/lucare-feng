package com.fcs.demo.rpc;

/**
 * Created by Lucare.Feng on 2016/7/10.
 */
public class RpcConsumer {
    public static void main(String[] args) throws Exception {
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        for (int i = 0;i<100;i++) {
            String hello = service.hello("word" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
