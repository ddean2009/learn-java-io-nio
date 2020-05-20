package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wayne
 * @version BufferUsage,  2020/5/19 10:11 上午
 */
@Slf4j
public class BufferUsage {

    @Test
    public void createBuffer(){
        IntBuffer intBuffer= IntBuffer.allocate(10);
        log.info("{}",intBuffer);
        log.info("{}",intBuffer.hasArray());
        int[] intArray=new int[10];
        IntBuffer intBuffer2= IntBuffer.wrap(intArray);
        log.info("{}",intBuffer2);
        IntBuffer intBuffer3= IntBuffer.wrap(intArray,2,5);
        log.info("{}",intBuffer3);
        intBuffer3.clear();
        log.info("{}",intBuffer3);
        log.info("{}",intBuffer3.hasArray());
    }

    @Test
    public void createByteBuffer() throws IOException {
        ByteBuffer byteBuffer= ByteBuffer.allocateDirect(10);
        log.info("{}",byteBuffer);
        log.info("{}",byteBuffer.hasArray());
        log.info("{}",byteBuffer.isDirect());

        try (RandomAccessFile aFile = new RandomAccessFile("src/main/resources/www.flydean.com", "r");
             FileChannel inChannel = aFile.getChannel()) {
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            log.info("{}",buffer);
            log.info("{}",buffer.hasArray());
            log.info("{}",buffer.isDirect());
        }
    }

    @Test
    public void putBuffer(){
        IntBuffer intBuffer= IntBuffer.allocate(10);
        intBuffer.put(1).put(2).put(3);
        log.info("{}",intBuffer.array());
        intBuffer.put(0,4);
        log.info("{}",intBuffer.array());
    }

    @Test
    public void flipBuffer(){
        IntBuffer intBuffer= IntBuffer.allocate(10);
        intBuffer.put(1).put(2).put(3);
        log.info("{}",intBuffer);
        intBuffer.flip();
        log.info("{}",intBuffer);
    }

    @Test
    public void getBuffer(){
        IntBuffer intBuffer= IntBuffer.allocate(10);
        intBuffer.put(1).put(2).put(3);
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            log.info("{}",intBuffer.get());
        }
        intBuffer.clear();
    }

    @Test
    public void rewindBuffer(){
        IntBuffer intBuffer= IntBuffer.allocate(10);
        intBuffer.put(1).put(2).put(3);
        log.info("{}",intBuffer);
        intBuffer.rewind();
        log.info("{}",intBuffer);
    }

    @Test
    public void useCompact(){
        IntBuffer intBuffer= IntBuffer.allocate(10);
        intBuffer.put(1).put(2).put(3);
        intBuffer.flip();
        log.info("{}",intBuffer);
        intBuffer.get();
        intBuffer.compact();
        log.info("{}",intBuffer);
        log.info("{}",intBuffer.array());
    }



    @Test
    public void duplicateBuffer(){
        IntBuffer intBuffer= IntBuffer.allocate(10);
        intBuffer.put(1).put(2).put(3);
        log.info("{}",intBuffer);
        IntBuffer duplicateBuffer=intBuffer.duplicate();
        log.info("{}",duplicateBuffer);
        IntBuffer readOnlyBuffer=intBuffer.asReadOnlyBuffer();
        log.info("{}",readOnlyBuffer);
        IntBuffer sliceBuffer=intBuffer.slice();
        log.info("{}",sliceBuffer);
    }


}
