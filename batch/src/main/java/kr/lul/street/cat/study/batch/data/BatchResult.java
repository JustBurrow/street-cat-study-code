package kr.lul.street.cat.study.batch.data;

import java.io.File;
import java.time.Duration;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2020/05/11
 */
public class BatchResult {
  private File file;
  private int dataCount;
  private int validCount;
  private Duration takes;

  public BatchResult(File file, int dataCount, int validCount, Duration takes) {
    this.file = file;
    this.dataCount = dataCount;
    this.validCount = validCount;
    this.takes = takes;
  }

  public File getFile() {
    return this.file;
  }

  public int getDataCount() {
    return this.dataCount;
  }

  public int getValidCount() {
    return this.validCount;
  }

  public Duration getTakes() {
    return this.takes;
  }

  @Override
  public String toString() {
    return format("(file=%s, dataCount=%s, validCount=%s, takes=%s)", this.file, this.dataCount, this.validCount, this.takes);
  }
}
