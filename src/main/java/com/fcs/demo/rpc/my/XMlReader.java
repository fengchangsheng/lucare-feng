package com.fcs.demo.rpc.my;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

/**
 * Created by Lucare.Feng on 2016/7/11.
 */
public class XMlReader {

    // 配置文件名
    private static String filename = "fs-remoting.xml";
    private static Remoting config;

    /**
     * 从配置文件中读取参数并保存到Config类中,
     * 很多时候程序中会多次使用到配置中的参数,
     * 于是设置成静态方法,读取一次后就一直保存其中的参数，
     * 不再反复读取
     *
     * @return
     */
    public static Remoting loadconfig() {
        if (config == null)
            config = getconfig();
        return config;
    }

    private static Remoting getconfig() {
        Remoting config = new Remoting();
        try {
            File f = new File(filename);
            if (!f.exists()) {
                System.out.println("  Error : Config file doesn't exist!");
                System.exit(1);
            }
            SAXReader reader = new SAXReader();
            Document doc;
            doc = reader.read(f);
            Element root = doc.getRootElement();
            Element data;
            Iterator<?> itr = root.elementIterator("service");
            while (itr.hasNext()) {
                data = (Element) itr.next();
                config.locator = data.elementText("locator").trim();
                config.port = data.elementText("port").trim();
            }

        } catch (Exception ex) {
            System.out.println("Error : " + ex.toString());
        }
        return config;

    }

    public static void main(String[] args) {
        loadconfig();
    }
}
