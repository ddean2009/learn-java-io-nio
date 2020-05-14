package com.flydean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wayne
 * @version CustResource,  2020/5/14 12:25 下午
 */
@Slf4j
public class CustResource implements AutoCloseable {

    public void helpSister(){
        log.info("帮助小师妹解决问题！");
    }

    @Override
    public void close() throws Exception {
        log.info("解决完问题，赶紧去吃饭！");
    }

    public static void main(String[] args) throws Exception {
       try( CustResource custResource= new CustResource()){
           custResource.helpSister();
       }
    }
}
