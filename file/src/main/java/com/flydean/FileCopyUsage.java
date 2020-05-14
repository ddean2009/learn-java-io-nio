package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author wayne
 * @version FileCopyUsage,  2020/5/14 5:56 下午
 */
@Slf4j
public class FileCopyUsage {

    @Test
    public  void copyWithFileStreams() throws IOException
    {
        File fileToCopy = new File("src/main/resources/www.flydean.com");
        File newFile = new File("src/main/resources/www.flydean.com.back");
        newFile.createNewFile();
        try(FileOutputStream output = new FileOutputStream(newFile);FileInputStream input = new FileInputStream(fileToCopy)){
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0)
            {
                output.write(buf, 0, bytesRead);
            }
        }
    }

    @Test
    public  void copyWithNIOChannel() throws IOException
    {
        File fileToCopy = new File("src/main/resources/www.flydean.com");
        File newFile = new File("src/main/resources/www.flydean.com.back");

        try(FileInputStream inputStream = new FileInputStream(fileToCopy);FileOutputStream outputStream = new FileOutputStream(newFile)){
            FileChannel inChannel = inputStream.getChannel();
            FileChannel outChannel = outputStream.getChannel();
            inChannel.transferTo(0, fileToCopy.length(), outChannel);
        }
    }

    @Test
    public  void copyWithNIOFiles() throws IOException
    {
        Path source = Paths.get("src/main/resources/www.flydean.com");
        Path destination = Paths.get("src/main/resources/www.flydean.com.back");
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    }
}
