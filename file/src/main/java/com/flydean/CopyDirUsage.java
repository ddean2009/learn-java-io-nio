package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author wayne
 * @version CopyDirUsage,  2020/5/16 7:58 下午
 */
@Slf4j
public class CopyDirUsage {

    @Test
    public void useCopyFolder() throws IOException {
        File sourceFolder = new File("src/main/resources/flydean-source");
        File destinationFolder = new File("src/main/resources/flydean-dest");
        copyFolder(sourceFolder, destinationFolder);
    }

    private static void copyFolder(File sourceFolder, File destinationFolder) throws IOException
    {
        //如果是dir则递归遍历创建dir，如果是文件则直接拷贝
        if (sourceFolder.isDirectory())
        {
            //查看目标dir是否存在
            if (!destinationFolder.exists())
            {
                destinationFolder.mkdir();
                log.info("目标dir已经创建: {}",destinationFolder);
            }
            for (String file : sourceFolder.list())
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);
                copyFolder(srcFile, destFile);
            }
        }
        else
        {
            //使用Files.copy来拷贝具体的文件
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            log.info("拷贝目标文件: {}",destinationFolder);
        }
    }
}
