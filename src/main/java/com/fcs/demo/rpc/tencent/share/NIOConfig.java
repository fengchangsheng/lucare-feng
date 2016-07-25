package com.fcs.demo.rpc.tencent.share;

/**
 * Created by Lucare.Feng on 2016/7/25.
 */
public interface NIOConfig {

    public boolean isKeepAlive();

    public boolean isTcpNoDeloy();

    public int getReactorCount();

    public String getReactorNamePrefix();
}
