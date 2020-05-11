package kr.lul.street.cat.study.batch.configuration;

import org.slf4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author justburrow
 * @since 2020/05/11
 */
@Configuration
@EnableAsync
public class BatchConfigurer {
  private static final Logger log = getLogger(BatchConfigurer.class);

  @Bean
  @ConfigurationProperties("kr.lul.street.cat.study.batch")
  public BatchProperties properties() {
    return new BatchProperties();
  }

  @Bean
  public Executor executor() {
    return new ThreadPoolTaskExecutor();
  }
}
