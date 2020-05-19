package com.flydean;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * @author wayne
 * @version PropertiesFileListener,  2020/5/18 7:35 上午
 */
@Slf4j
public class PropertiesFileListener implements Runnable{

    private String fileName = null;
    private String filePath = null;

    public PropertiesFileListener(final String filePath) {
        this.filePath = filePath;
    }

    public void run() {
        try {
            watch(filePath);
        } catch (IOException e) {
           log.error(e.getMessage());
        }
    }

    private void watch(final String file) throws IOException {
        final int lastIndex = file.lastIndexOf("/");
        String dirPath = file.substring(0, lastIndex + 1);
        String fileName = file.substring(lastIndex + 1, file.length());
        this.fileName = fileName;

        loadConfig(file);
        startWatcher(dirPath, fileName);
    }

    private void startWatcher(String dirPath, String file) throws IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(dirPath);
        path.register(watchService, ENTRY_MODIFY);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                watchService.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }));

        WatchKey key = null;
        while (true) {
            try {
                key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.context().toString().equals(fileName)) {
                        loadConfig(dirPath + file);
                    }
                }
                boolean reset = key.reset();
                if (!reset) {
                    log.info("该文件无法重置");
                    break;
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public void loadConfig(final String file) throws IOException {
        log.info("load config file {}", file);
        PropertiesUtil.getInstance().loadProperties(file);
    }
}
