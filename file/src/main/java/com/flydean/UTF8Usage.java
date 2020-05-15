package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author wayne
 * @version UTF8Usage,  2020/5/15 2:24 下午
 */
@Slf4j
public class UTF8Usage {

    @Test
    public void writeUTF8() throws IOException {

            File fileDir = new File("src/main/resources/www.flydean.com.properties.utf8");
            try(Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), StandardCharsets.UTF_8))){
                out.append("www.flydean.com").append("\n");
                out.append("程序那些事").append("\n");
                out.flush();
            }
    }

    @Test
    public void readUTF8() throws IOException {
        File fileDir = new File("src/main/resources/www.flydean.com.properties.utf8");
        try(BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileDir), StandardCharsets.UTF_8))){
        String str;
        while ((str = in.readLine()) != null) {
            log.info(str);
        }
    }
}
}
