package kr.lul.street.cat.study.batch.configuration;

import java.io.File;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2020/05/11
 */
public class BatchProperties {
  private File inputDirectory;

  public BatchProperties() {
  }

  public File getInputDirectory() {
    return this.inputDirectory;
  }

  public void setInputDirectory(File inputDirectory) {
    this.inputDirectory = inputDirectory;
  }

  @Override
  public String toString() {
    return format("(inputDirectory=%s)", this.inputDirectory);
  }
}
