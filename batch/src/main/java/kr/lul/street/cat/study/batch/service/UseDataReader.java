package kr.lul.street.cat.study.batch.service;

import kr.lul.street.cat.study.batch.data.DeviceType;
import kr.lul.street.cat.study.batch.data.UseData;
import org.apache.commons.csv.CSVFormat;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author justburrow
 * @since 2020/05/12
 */
@Service
public class UseDataReader {
  private static final Logger log = getLogger(UseDataReader.class);

  private CSVFormat format;
  private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @PostConstruct
  private void postConstruct() {
    this.format = CSVFormat.DEFAULT;
  }

  public List<UseData> load(File file) {
    try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
      return stream(this.format.parse(reader).spliterator(), false)
                 .map(record -> new UseData(
                     UUID.fromString(record.get(0)),
                     UUID.fromString(record.get(1)),
                     DeviceType.valueOf(parseInt(record.get(2))),
                     parseInt(record.get(3)),
                     LocalDateTime.parse(record.get(4), this.dateTimeFormatter)))
                 .collect(toList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
