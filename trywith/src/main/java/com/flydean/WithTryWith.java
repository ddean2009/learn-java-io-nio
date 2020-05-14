package com.flydean;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author wayne
 * @version WithTryWith,  2020/5/14 10:11 上午
 */
@Slf4j
public class WithTryWith {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("trywith/src/main/resources/www.flydean.com")))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                log.info(sCurrentLine);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
