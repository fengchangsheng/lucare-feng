package com.fcs.demo.rpc.tencent.server;

import com.fcs.demo.rpc.tencent.transpot.ReactorManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by Lucare.Feng on 2016/7/25.
 */
public class KeystoneServer {

    private ReactorManager reactorManager;

    private String serverRoot = null;

    public void start() throws IOException {
        ServerConfig sc = loadConf();
    }


    private ServerConfig loadConf() throws IOException {
        return ServerConfigParser.parse(getServerRoot()) ;
    }

    private String getServerRoot() throws IOException {
        if(serverRoot==null)
        {
            String root = System.getProperty("server.root") ;
            if(root==null) root = "." ;
            File file = new File(root) ;
            serverRoot = file.getCanonicalPath() ;
        }
        return serverRoot ;
    }

}
