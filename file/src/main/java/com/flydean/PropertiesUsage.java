package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author wayne
 * @version PropertiesUsage,  2020/5/15 1:50 下午
 */
@Slf4j
public class PropertiesUsage {

    @Test
    public void usePropertiesFile() throws IOException {
        Properties configProp = new Properties();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("www.flydean.com.properties");
        configProp.load(in);
        log.info(configProp.getProperty("name"));
        configProp.setProperty("name", "www.flydean.com");
        log.info(configProp.getProperty("name"));
    }

    @Test
    public void usePropertiesWithUTF8() throws IOException{
        Properties configProp = new Properties();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("www.flydean.com.properties");
        InputStreamReader inputStreamReader= new InputStreamReader(in, StandardCharsets.UTF_8);
        configProp.load(inputStreamReader);
        log.info(configProp.getProperty("name"));
        configProp.setProperty("name", "www.flydean.com");
        log.info(configProp.getProperty("name"));
    }

    @Test
    public void usePropertiesFileWithTransfer() throws IOException {
        Properties configProp = new Properties();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("www.flydean.com.properties.cn");
        configProp.load(in);
        log.info(configProp.getProperty("name"));
        configProp.setProperty("name", "www.flydean.com");
        log.info(configProp.getProperty("name"));
    }
}
