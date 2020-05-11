package kr.lul.street.cat.study.batch.monitor;

import kr.lul.street.cat.study.batch.configuration.BatchProperties;
import kr.lul.street.cat.study.batch.data.BatchResult;
import kr.lul.street.cat.study.batch.service.BatchService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Future;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author justburrow
 * @since 2020/05/11
 */
@Component
public class InputDirectoryMonitor {
  private static final Logger log = getLogger(InputDirectoryMonitor.class);

  @Autowired
  private BatchProperties properties;

  @Autowired
  private BatchService service;

  private WatchService watchService;
  private Thread listener;

  @PostConstruct
  private void postConstruct() throws IOException {
    log.info("#postConstruct start.");

    this.watchService = FileSystems.getDefault().newWatchService();
    Path path = Paths.get(this.properties.getInputDirectory().toURI());

    path.register(this.watchService, ENTRY_CREATE, ENTRY_MODIFY);

    this.listener = new Thread(this::monitor, InputDirectoryMonitor.class.getName() + ".listener");
    this.listener.start();
  }

  @PreDestroy
  private void preDestroy() {
    log.info("#preDestroy interrupt listenerThread={}", this.listener);
    this.listener.interrupt();
  }

  private void monitor() {
    WatchKey key;
    try {
      while ((key = this.watchService.take()) != null) {
        key.pollEvents()
            .forEach(event -> {
              File file = new File(this.properties.getInputDirectory(), event.context().toString());
              log.info("#monitor event : kind={}, file={}", event.kind(), file.getAbsolutePath());

              Future<BatchResult> result = this.service.process(file);
            });
        key.reset();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
