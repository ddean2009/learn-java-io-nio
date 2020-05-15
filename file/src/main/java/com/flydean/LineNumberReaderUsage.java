package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * @author wayne
 * @version LineNumberReaderUsage,  2020/5/15 9:47 上午
 * 如果我们在解析文件的时候，需要找出哪一行文件出错了，可以使用LineNumberReader来打印出出错的行数，非常实在的一个功能
 */
@Slf4j
public class LineNumberReaderUsage {

    @Test
    public void useLineNumberReader() throws IOException {
        try(LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("src/main/resources/www.flydean.com")))
        {
            //输出初始行数
            log.info("Line {}" , lineNumberReader.getLineNumber());
            //重置行数
            lineNumberReader.setLineNumber(2);
            //获取现有行数
            log.info("Line {} ", lineNumberReader.getLineNumber());
            //读取所有文件内容
            String line = null;
            while ((line = lineNumberReader.readLine()) != null)
            {
                log.info("Line {} is : {}" , lineNumberReader.getLineNumber() , line);
            }
        }
    }
}
