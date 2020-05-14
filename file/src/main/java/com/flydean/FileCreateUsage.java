package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * @author wayne
 * @version FileUsage,  2020/5/14 2:44 下午
 */
@Slf4j
public class FileCreateUsage {

    @Test
    public void createFileWithFile() throws IOException {
        File file = new File("src/main/resources/www.flydean.com");
        //Create the file
        if (file.createNewFile()){
            log.info("恭喜，文件创建成功");
        }else{
            log.info("不好意思，文件创建失败");
        }
        //Write Content
        try(FileWriter writer = new FileWriter(file)){
            writer.write("www.flydean.com");
        }
    }

    @Test
    public  void createFileWithStream() throws IOException
    {
        String data = "www.flydean.com";
        try(FileOutputStream out = new FileOutputStream("src/main/resources/www.flydean.com")){
            out.write(data.getBytes());
        }
    }

    @Test
    public void createFileWithNIO()  throws IOException
    {
        String data = "www.flydean.com";
        Files.write(Paths.get("src/main/resources/www.flydean.com"), data.getBytes());

        List<String> lines = Arrays.asList("程序那些事", "www.flydean.com");
        Files.write(Paths.get("src/main/resources/www.flydean.com"),
                lines,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    @Test
    public void fileWithPromission() throws IOException {
        File file = File.createTempFile("src/main/resources/www.flydean.com","");
        log.info("{}",file.exists());

        file.setExecutable(true);
        file.setReadable(true,true);
        file.setWritable(true);
        log.info("{}",file.canExecute());
        log.info("{}",file.canRead());
        log.info("{}",file.canWrite());

        Path path = Files.createTempFile("src/main/resources/www.flydean.com", "");
        log.info("{}",Files.exists(path));
        log.info("{}",Files.isReadable(path));
        log.info("{}",Files.isWritable(path));
        log.info("{}",Files.isExecutable(path));
    }
}
