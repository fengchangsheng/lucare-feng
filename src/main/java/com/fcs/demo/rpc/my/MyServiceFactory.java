package com.fcs.demo.rpc.my;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.Map;


/**
 * Created by Lucare.Feng on 2016/7/12.
 * servie工厂
 */
public class MyServiceFactory {

    private static Map<String,Remoting> config;

    static {
        InputStream inputStream = null;
        inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(XMlReader.filename);
        if(inputStream == null) {
            throw new IllegalArgumentException("xml file not found in classpath: " + XMlReader.filename);
        }
        config  = XMlReader.loadconfig(inputStream);
    }

    public static <T> T getService(Class<T> interfaceClass){
        if (interfaceClass == null) {
            throw new IllegalArgumentException("Interface class == null");
        }
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException("The "+interfaceClass.getName()+" must be interface class!");
        }
        Remoting remoting = config.get(interfaceClass.getName());
        final String host = remoting.locator;
        final int port = Integer.parseInt(remoting.port);
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("Host == null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port "+port);
        }
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket(host,port);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeUTF(method.getName());
                outputStream.writeObject(method.getParameterTypes());
                outputStream.writeObject(args);
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                Object result = inputStream.readObject();
                if (result instanceof Throwable)
                    throw (Throwable) result;
                return result;
            }
        });
    }
}
