package kr.lul.street.cat.study.batch.service;

import kr.lul.street.cat.study.batch.data.BatchResult;
import kr.lul.street.cat.study.batch.data.UseData;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.concurrent.Future;

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

  @Async
  public Future<BatchResult> process(File file) {
    if (log.isTraceEnabled())
      log.trace("#process args : file={}", file.getAbsoluteFile());

    List<UseData> data = this.reader.load(file);
    if (log.isTraceEnabled())
      log.trace("#process data={}", data);

    BatchResult result = new BatchResult();
    return new AsyncResult<>(result);
  }
}
