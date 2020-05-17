package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author wayne
 * @version TempFileUsage,  2020/5/16 4:45 下午
 */
@Slf4j
public class TempFileUsage {

    @Test
    public void useFile() throws IOException {
        File file = File.createTempFile("src/main/resources/www.flydean.com", ".txt");
        log.info("{}",file.getAbsolutePath());
        file.delete();
        file.deleteOnExit();
    }

    @Test
    public void useNIO() throws IOException {
            Path path = Files.createTempFile("src/main/resources/www.flydean.com", ".txt");
            log.info("Temp file : {} " , path);
            //Delete file on exit
            path.toFile().deleteOnExit();
//            Files.deleteIfExists(path);
//            Files.delete(path);
    }
}
