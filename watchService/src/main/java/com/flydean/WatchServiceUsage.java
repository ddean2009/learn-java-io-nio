package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wayne
 * @version WatchServiceUsage,  2020/5/18 11:12 上午
 */
@Slf4j
public class WatchServiceUsage {

    @Test
    public void useWatcher() throws InterruptedException {
        PropertiesFileListener propertiesFileListener= new PropertiesFileListener("src/main/resources/www.flydean.com");
        new Thread(propertiesFileListener).start();
        while (true) {
            Thread.sleep(2000);
            log.info(PropertiesUtil.getInstance().getProperty("value"));
        }
    }
}
