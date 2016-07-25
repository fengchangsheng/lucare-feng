package com.fcs.demo.rpc.tencent.server;

import com.fcs.demo.rpc.tencent.transpot.BaseNIOConfig;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lucare.Feng on 2016/7/25.
 */
public class ServerConfig extends BaseNIOConfig {

    private String host = "127.0.0.1";
    private int tcpPort = 12580;
    private int backlog = 1024;

    private String messageHandlerClass = "com.keystone.server.MultiplexingMessageHandler";

    private int minThreadPoolSize = 20;
    private int maxThreadPoolSize = 512;
    private int threadPoolQueueSize = 200;
    private int sessionTimeout = (int) TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES);
    private int sessionCheckInterval = (int) TimeUnit.MILLISECONDS.convert(30, TimeUnit.SECONDS);

    private int logRate = 5;
    private String defaultApp = null;

    public ServerConfig(int reactorCount){
        this(reactorCount,"ks-server-reactor","com.keystone.server.KeystoneServerEventHandler");
    }

    public ServerConfig(int reactorCount, String reactorNamePrefix, String eventHandlerClass) {
        super(reactorCount, reactorNamePrefix, eventHandlerClass);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public int getBacklog() {
        return backlog;
    }

    public void setBacklog(int backlog) {
        this.backlog = backlog;
    }

    public String getMessageHandlerClass() {
        return messageHandlerClass;
    }

    public void setMessageHandlerClass(String messageHandlerClass) {
        this.messageHandlerClass = messageHandlerClass;
    }

    public int getMinThreadPoolSize() {
        return minThreadPoolSize;
    }

    public void setMinThreadPoolSize(int minThreadPoolSize) {
        this.minThreadPoolSize = minThreadPoolSize;
    }

    public int getMaxThreadPoolSize() {
        return maxThreadPoolSize;
    }

    public void setMaxThreadPoolSize(int maxThreadPoolSize) {
        this.maxThreadPoolSize = maxThreadPoolSize;
    }

    public int getThreadPoolQueueSize() {
        return threadPoolQueueSize;
    }

    public void setThreadPoolQueueSize(int threadPoolQueueSize) {
        this.threadPoolQueueSize = threadPoolQueueSize;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeOut) {
        this.sessionTimeout = sessionTimeOut;
    }

    public int getSessionCheckInterval() {
        return sessionCheckInterval;
    }

    public void setSessionCheckInterval(int sessionCheckInterval) {
        this.sessionCheckInterval = sessionCheckInterval;
    }

    public int getLogRate() {
        return logRate;
    }

    public void setLogRate(int logRate) {
        this.logRate = logRate;
    }

    public String getDefaultApp() {
        return defaultApp;
    }

    public void setDefaultApp(String defaultApp) {
        this.defaultApp = defaultApp;
    }
}
