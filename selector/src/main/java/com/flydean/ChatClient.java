package com.flydean;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author wayne
 * @version ChatClient,  2020/5/22 7:57 下午
 */
@Slf4j
public class ChatClient {

    private static SocketChannel socketChannel;
    private static ByteBuffer byteBuffer;

    public static void main(String[] args) throws IOException {

        ChatClient chatClient = new ChatClient();
        String response = chatClient.sendMessage("hello 小师妹！");
        log.info("response is {}", response);
        response = chatClient.sendMessage("能不能？");
        log.info("response is {}", response);
        chatClient.stop();

    }

    public void stop() throws IOException {
        socketChannel.close();
        byteBuffer = null;
    }

    public ChatClient() throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9527));
        byteBuffer = ByteBuffer.allocate(512);
    }

    public String sendMessage(String msg) throws IOException {
        byteBuffer = ByteBuffer.wrap(msg.getBytes());
        String response = null;
        socketChannel.write(byteBuffer);
        byteBuffer.clear();
        socketChannel.read(byteBuffer);
        byteBuffer.flip();
        byte[] bytes= new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        response =new String(bytes).trim();
        byteBuffer.clear();
        return response;
    }
}
