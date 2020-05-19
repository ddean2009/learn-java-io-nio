package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author wayne
 * @version com.flydean.FilePathUsage,  2020/5/18 5:59 下午
 */
@Slf4j
public class FilePathUsage {

    @Test
    public void getFilePath() throws IOException {
        File file= new File("../../www.flydean.com.txt");
        log.info("name is : {}",file.getName());

        log.info("path is : {}",file.getPath());
        log.info("absolutePath is : {}",file.getAbsolutePath());
        log.info("canonicalPath is : {}",file.getCanonicalPath());
    }

    @Test
    public void getAbsolutePath(){
        Path absolutePath = Paths.get("/data/flydean/learn-java-io-nio/file-path", "src/resource","www.flydean.com.txt");
        log.info("absolutePath {}",absolutePath );
    }

    @Test
    public void getRelativePath(){
        Path RelativePath = Paths.get("src", "resource","www.flydean.com.txt");
        log.info("absolutePath {}",RelativePath.toAbsolutePath() );
    }

    @Test
    public void getPathfromURI(){
        URI uri = URI.create("file:///data/flydean/learn-java-io-nio/file-path/src/resource/www.flydean.com.txt");
        log.info("schema {}",uri.getScheme());
        log.info("default provider absolutePath {}",FileSystems.getDefault().provider().getPath(uri).toAbsolutePath().toString());
    }

        @Test
        public void getPathWithFileSystem(){
            Path path1 = FileSystems.getDefault().getPath(System.getProperty("user.home"), "flydean", "flydean.txt");
           log.info(path1.toAbsolutePath().toString());

            Path path2 = FileSystems.getDefault().getPath("/Users", "flydean", "flydean.txt");
            log.info(path2.toAbsolutePath().toString());

        }
}
