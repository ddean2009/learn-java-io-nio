package com.flydean;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author wayne
 * @version WithOutTryWith,  2020/5/14 10:08 上午
 */
@Slf4j
public class WithOutTryWith {

    public static void main(String[] args) {
        BufferedReader bufferedReader = null;
        try {
            String line;
            bufferedReader = new BufferedReader(new FileReader("trywith/src/main/resources/www.flydean.com"));
            while ((line = bufferedReader.readLine()) != null) {
                log.info(line);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (bufferedReader != null){
                 bufferedReader.close();
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }
}
