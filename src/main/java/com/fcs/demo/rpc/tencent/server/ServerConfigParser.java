package com.fcs.demo.rpc.tencent.server;

import com.fcs.demo.rpc.tencent.support.common.CommonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Lucare.Feng on 2016/7/25.
 */
public class ServerConfigParser {

    private ServerConfigParser(){

    }

    public static ServerConfig parse(String serverRoot) throws IOException {
        return doParse(serverRoot);
    }

    private static ServerConfig doParse(String serverRoot) throws IOException {
        String namePath = CommonUtils.merges(serverRoot, "conf", "server.conf");
        Properties conf = new Properties();
        InputStreamReader reader = new InputStreamReader(new FileInputStream(namePath));
        conf.load(reader);

        String host = conf.getProperty("ksserver.host");
        int port = Integer.valueOf(conf.getProperty("ksserver.port").trim()) ;
        int minThreadPoolSize = Integer.valueOf(conf.getProperty("ksserver.minThreadPoolSize").trim()) ;
        int maxThreadPoolSize = Integer.valueOf(conf.getProperty("ksserver.maxThreadPoolSize").trim()) ;
        int threadPoolQueueSize = Integer.valueOf(conf.getProperty("ksserver.threadPoolQueueSize").trim()) ;
        int sessionTimeout = Integer.valueOf(conf.getProperty("ksserver.sessionTimeout").trim()) ;
        int sessionCheckInterval = Integer.valueOf(conf.getProperty("ksserver.sessionCheckInterval").trim()) ;
        int logRate = Integer.valueOf(conf.getProperty("ksserver.logRate").trim()) ;
        String defaultApp = conf.getProperty("ksserver.defaultApp") ;

        reader.close();
        ServerConfig sc = new ServerConfig(cauculateRactorCount()) ;
        sc.setBacklog(1024);//TODO:1024
        sc.setHost(host);
        sc.setTcpPort(port);
        sc.setMinThreadPoolSize(minThreadPoolSize);
        sc.setMaxThreadPoolSize(maxThreadPoolSize);
        sc.setThreadPoolQueueSize(threadPoolQueueSize) ;
        sc.setSessionTimeout(sessionTimeout);
        sc.setSessionCheckInterval(sessionCheckInterval);
        sc.setLogRate(logRate);
        sc.setDefaultApp(defaultApp);
        return sc ;
    }

    public static int cauculateRactorCount() {
        int processors = Runtime.getRuntime().availableProcessors();
        return processors > 8 ? 4 + (processors * 5 / 8) : processors + 1;
    }
}
