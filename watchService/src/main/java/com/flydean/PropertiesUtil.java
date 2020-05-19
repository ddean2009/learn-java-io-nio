package com.flydean;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

/**
 * @author wayne
 * @version PropertiesUtil,  2020/5/17 10:47 下午
 */
@Slf4j
public class PropertiesUtil {

    private static final Properties properties = new Properties();

    private static class PropertiesUtilHolder{
        private static final PropertiesUtil propertiesUtil= new PropertiesUtil();
    }

    public static PropertiesUtil getInstance(){
        return PropertiesUtilHolder.propertiesUtil;
    }

    public void loadProperties(final String file) throws IOException {
        try(InputStream in = new FileInputStream(new File(file))) {
            properties.load(in);
        }
    }

    public String getProperty(final String key){
        return (String) properties.get(key);
    }

}
