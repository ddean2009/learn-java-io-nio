package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/**
 * @author wayne
 * @version FileWriterUsage,  2020/5/16 6:00 下午
 */
@Slf4j
public class FileWriterUsage {

    @Test
    public void useBufferedWriter() throws IOException {
        String content = "www.flydean.com";
        File file = new File("src/main/resources/www.flydean.com");

        FileWriter fw = new FileWriter(file);
        try(BufferedWriter bw = new BufferedWriter(fw)){
            bw.write(content);
        }
    }

    @Test
    public void usePrintWriter() throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/www.flydean.com");
        try(PrintWriter printWriter = new PrintWriter(fileWriter)){
            printWriter.print("www.flydean.com");
            printWriter.printf("程序那些事 %s ", "非常棒");
        }
    }

    @Test
    public void useFileOutputStream() throws IOException {
        String str = "www.flydean.com";
        try(FileOutputStream outputStream = new FileOutputStream("src/main/resources/www.flydean.com");
            BufferedOutputStream bufferedOutputStream= new BufferedOutputStream(outputStream)){
            byte[] strToBytes = str.getBytes();
            bufferedOutputStream.write(strToBytes);
        }
    }

    @Test
    public void useDataOutPutStream()
            throws IOException {
        String value = "www.flydean.com";
        try(FileOutputStream fos = new FileOutputStream("src/main/resources/www.flydean.com")){
            DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
            outStream.writeUTF(value);
        }
    }

    @Test
    public void useRandomAccess() throws IOException {
        try(RandomAccessFile writer = new RandomAccessFile("src/main/resources/www.flydean.com", "rw")){
            writer.seek(100);
            writer.writeInt(50);
        }
    }

    @Test
    public void useFileLock()
            throws IOException {
        try(RandomAccessFile stream = new RandomAccessFile("src/main/resources/www.flydean.com", "rw");
        FileChannel channel = stream.getChannel()){
            FileLock lock = null;
            try {
                lock = channel.tryLock();
            } catch (final OverlappingFileLockException e) {
                stream.close();
                channel.close();
            }
            stream.writeChars("www.flydean.com");
            lock.release();
        }
    }
}
