package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

/**
 * @author wayne
 * @version FileNameFilterUsage,  2020/5/14 8:00 下午
 */
@Slf4j
public class FileNameFilterUsage {

    @Test
    public void useFileNameFilter()
    {
        String targetDirectory = "src/main/resources/";
        File directory = new File(targetDirectory);

        //Filter out all log files
        String[] logFiles = directory.list( (dir, fileName)-> fileName.endsWith(".log"));

        //If no log file found; no need to go further
        if (logFiles.length == 0)
            return;

        //This code will delete all log files one by one
        for (String logfile : logFiles)
        {
            String tempLogFile = targetDirectory + File.separator + logfile;
            File fileDelete = new File(tempLogFile);
            boolean isdeleted = fileDelete.delete();
            log.info("file : {} is deleted : {} ", tempLogFile , isdeleted);
        }
    }

    @Test
    public void useFileFilter(){
        String targetDirectory = "src/main/resources/";
        File dir = new File(targetDirectory);

        //Filter out all log files
        File[] logFiles = dir.listFiles(pathname -> pathname.getName().endsWith(".log"));

        //If no log file found; no need to go further
        if (logFiles.length == 0)
            return;

        //This code will delete all log files one by one
        for (File logfile : logFiles)
        {
            boolean isdeleted = logfile.delete();
            log.info("file : {} is deleted : {} ", logfile , isdeleted);
        }
    }
}
