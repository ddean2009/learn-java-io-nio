package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/**
 * @author wayne
 * @version DeleteDirUsage,  2020/5/16 7:42 下午
 */
@Slf4j
public class IterateDirUsage {

    @Test
    public void useFileWalkToDelete() throws IOException {
        Path dir = Paths.get("src/main/resources/flydean");
        Files.walk(dir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    public void useFileWalkToSumSize() throws IOException {

        Path folder = Paths.get("src/test/resources");
        long size = Files.walk(folder)
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();
        log.info("dir size is: {}",size);
    }

}
