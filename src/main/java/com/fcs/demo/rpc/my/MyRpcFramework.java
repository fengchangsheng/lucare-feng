package com.fcs.demo.rpc.my;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Lucare.Feng on 2016/7/11.
 */
public class MyRpcFramework {

    public static void export(final Object service, int port) throws Exception{
        if (service == null) {
            throw new IllegalArgumentException("service instance == null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port "+port);
        }
        System.out.println("Export service "+service.getClass().getName()+" on port "+port);
        ServerSocket serverSocket = new ServerSocket(port);
        for (;;) {
            try {
                final Socket socket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                            String methodName = objectInputStream.readUTF();
                            Class<?>[] parameterTypes = (Class<?>[]) objectInputStream.readObject();
                            Object[] arguments = (Object[]) objectInputStream.readObject();
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                            Method method = service.getClass().getMethod(methodName, parameterTypes);
                            Object result = method.invoke(service, arguments);
                            objectOutputStream.writeObject(result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception{
        if (interfaceClass == null) {
            throw new IllegalArgumentException("Interface class == null");
        }
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException("The "+interfaceClass.getName()+" must be interface class!");
        }
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
