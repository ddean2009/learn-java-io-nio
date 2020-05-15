package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wayne
 * @version FileReadUsage,  2020/5/15 9:34 上午
 */
@Slf4j
public class FileReadUsage {

    //读取字符码
    @Test
    public void withFileReader() throws IOException {
        File file = new File("src/main/resources/www.flydean.com");

        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("www.flydean.com")) {
                    log.info(line);
                }
            }
        }
    }

    @Test
    public void withStream() throws IOException {
        Path filePath = Paths.get("src/main/resources", "www.flydean.com");
        try (Stream<String> lines = Files.lines(filePath))
        {
            List<String> filteredLines = lines.filter(s -> s.contains("www.flydean.com"))
                    .collect(Collectors.toList());
            filteredLines.forEach(log::info);
        }
    }

    @Test
    public void withScanner() throws FileNotFoundException {
        FileInputStream fin = new FileInputStream(new File("src/main/resources/www.flydean.com"));
        Scanner scanner = new Scanner(fin,"UTF-8").useDelimiter("\n");
        String theString = scanner.hasNext() ? scanner.next() : "";
        log.info(theString);
        scanner.close();
    }

    //读取字节码
    @Test
    public void readBytes() throws IOException {
        Path path = Paths.get("src/main/resources/www.flydean.com");
        byte[] data = Files.readAllBytes(path);
        log.info("{}",data);
    }

    @Test
    public void readWithStream() throws IOException {
        File file = new File("src/main/resources/www.flydean.com");
        byte[] bFile = new byte[(int) file.length()];
        try(FileInputStream fileInputStream  = new FileInputStream(file))
        {
            fileInputStream.read(bFile);
            for (int i = 0; i < bFile.length; i++) {
                log.info("{}",bFile[i]);
            }
        }
    }

    @Test
    public void readWithBlock() throws IOException {
        try (RandomAccessFile aFile = new RandomAccessFile("src/main/resources/www.flydean.com", "r");
             FileChannel inChannel = aFile.getChannel();) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++) {
                    log.info("{}", buffer.get());
                }
                buffer.clear();
            }
        }
    }

    @Test
    public void copyWithMap() throws IOException{
        try (RandomAccessFile aFile = new RandomAccessFile("src/main/resources/www.flydean.com", "r");
             FileChannel inChannel = aFile.getChannel()) {
             MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
             buffer.load();
            for (int i = 0; i < buffer.limit(); i++)
            {
                log.info("{}", buffer.get());
            }
            buffer.clear();
        }
    }

}

