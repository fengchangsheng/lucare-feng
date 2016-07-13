package com.fcs.demo.rpc.my;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Lucare.Feng on 2016/7/11.
 */
public class XMlReader {

    // 配置文件名
    public static String filename = "fs-remoting.xml";
    private static Map<String,Remoting> configs;


    /**
     * 从配置文件中读取参数并保存到Config类中,
     * 很多时候程序中会多次使用到配置中的参数,
     * 于是设置成静态方法,读取一次后就一直保存其中的参数，
     * 不再反复读取
     *
     * @return
     */
    public static Map<String,Remoting> loadconfig(InputStream inputStream) {
        if (configs == null)
            configs = getconfig(inputStream);
        return configs;
    }

    private static Map<String,Remoting> getconfig(InputStream inputStream) {
        try {
            Map<String, Remoting> map = new HashMap<String, Remoting>();
            Remoting config = null;
            SAXReader reader = new SAXReader();
            Document doc;
            doc = reader.read(inputStream);
            Element root = doc.getRootElement();
            Element data;
            Iterator<?> itr = root.elementIterator("service");
            while (itr.hasNext()) {
                config = new Remoting();
                data = (Element) itr.next();
                config.locator = data.elementText("locator").trim();
                config.port = data.elementText("port").trim();
                config.api = data.elementText("api").trim();
                config.clasz = data.elementText("class").trim();
//                String api = data.attribute("name").getText();
                map.put(config.api,config);
            }
            return map;

        } catch (Exception ex) {
            System.out.println("Error : " + ex.toString());
        }
        return null;

    }

}
