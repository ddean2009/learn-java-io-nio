package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author wayne
 * @version ChannelUsage,  2020/5/20 10:32 上午
 */
@Slf4j
public class ChannelUsage {

    @Test
    public void useChannelCopy() throws IOException {
        FileInputStream input = new FileInputStream ("src/main/resources/www.flydean.com");
        FileOutputStream output = new FileOutputStream ("src/main/resources/www.flydean.com.txt");
        try(ReadableByteChannel source = input.getChannel(); WritableByteChannel dest = output.getChannel()){
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (source.read(buffer) != -1)
            {
                // flip buffer,准备写入
                buffer.flip();
                // 查看是否有更多的内容
                while (buffer.hasRemaining())
                {
                    dest.write(buffer);
                }
                // clear buffer，供下一次使用
                buffer.clear();
            }
        }
    }
}
