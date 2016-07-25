package com.fcs.demo.rpc.tencent.transpot;

import com.fcs.demo.rpc.tencent.share.NIOConfig;

/**
 * Created by Lucare.Feng on 2016/7/25.
 */
public class BaseNIOConfig implements NIOConfig {

    private boolean isKeepAlive = true;
    private boolean isTcpNoDelay = true;
    private int reactorCount;

    private String reactorNamePrefix;
    private String protocolFactoryClass = "";
    private String eventHandlerClass;

    public BaseNIOConfig(int reactorCount, String reactorNamePrefix, String eventHandlerClass) {
        this.reactorCount = reactorCount;
        this.reactorNamePrefix = reactorNamePrefix;
        this.eventHandlerClass = eventHandlerClass;
    }

    @Override
    public boolean isKeepAlive() {
        return isKeepAlive;
    }

    @Override
    public boolean isTcpNoDeloy() {
        return isTcpNoDelay;
    }

    @Override
    public int getReactorCount() {
        return reactorCount;
    }

    @Override
    public String getReactorNamePrefix() {
        return reactorNamePrefix;
    }

    public void setKeepAlive(boolean keepAlive) {
        isKeepAlive = keepAlive;
    }

    public void setTcpNoDelay(boolean tcpNoDelay) {
        isTcpNoDelay = tcpNoDelay;
    }

    public void setReactorCount(int reactorCount) {
        this.reactorCount = reactorCount;
    }

    public String getProtocolFactoryClass() {
        return protocolFactoryClass;
    }

    public void setReactorNamePrefix(String reactorNamePrefix) {
        this.reactorNamePrefix = reactorNamePrefix;
    }

    public void setProtocolFactoryClass(String protocolFactoryClass) {
        this.protocolFactoryClass = protocolFactoryClass;
    }

    public String getEventHandlerClass() {
        return eventHandlerClass;
    }

    public void setEventHandlerClass(String eventHandlerClass) {
        this.eventHandlerClass = eventHandlerClass;
    }
}
