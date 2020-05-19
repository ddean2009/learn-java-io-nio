package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URL;

/**
 * @author wayne
 * @version JRTFileSystemUsage,  2020/5/18 2:12 下午
 */
@Slf4j
public class JRTFileSystemUsage {

    @Test
    public void useJRTFileSystem(){
        String resource = "java/lang/Object.class";
        URL url = ClassLoader.getSystemResource(resource);
        log.info("{}",url);
    }

}
