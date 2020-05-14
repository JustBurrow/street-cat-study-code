package kr.lul.street.cat.study.batch.service;

import kr.lul.street.cat.study.batch.data.BatchResult;
import kr.lul.street.cat.study.batch.data.Use;
import kr.lul.street.cat.study.batch.repository.UseRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author justburrow
 * @since 2020/05/11
 */
@Service
public class BatchService {
  private static final Logger log = getLogger(BatchService.class);

  @Autowired
  private UseDataReader reader;
  @Autowired
  private ChipService chipService;
  @Autowired
  private DeviceService deviceService;
  @Autowired
  private UseRepository useRepository;

  @Async
  public Future<BatchResult> process(File file) {
    if (log.isTraceEnabled())
      log.trace("#process args : file={}", file.getAbsoluteFile());

    Instant start = Instant.now();
    AtomicInteger dataCounter = new AtomicInteger();
    AtomicInteger validCounter = new AtomicInteger();
    this.reader.load(file)
        .stream()
        .peek(useData -> dataCounter.incrementAndGet())
        .filter(useData -> this.chipService.validate(useData.getChipId()))
        .filter(useData -> this.deviceService.validate(useData.getDeviceId()))
        .forEach(useData -> {
          Use use = new Use(useData.getChipId(), useData.getDeviceId(),
              useData.getType(), useData.getValue(),
              useData.getMeasuredAt());
          use = this.useRepository.save(use);
          int i = validCounter.incrementAndGet();
          if (log.isTraceEnabled())
            log.trace("#process num={}, use={}", i, use);
        });

    BatchResult result = new BatchResult(file, dataCounter.get(), validCounter.get(), Duration.between(start, Instant.now()));
    if (log.isTraceEnabled())
      log.trace("#process return : {}", result);
    return new AsyncResult<>(result);
  }
}
