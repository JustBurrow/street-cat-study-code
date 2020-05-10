package kr.lul.street.cat.study.api.service;

import kr.lul.street.cat.study.api.ApiTestConfiguration;
import kr.lul.street.cat.study.api.data.Cat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.Thread.sleep;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author justburrow
 * @since 2020/05/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiTestConfiguration.class)
@Transactional
public class CatServiceTest {
  private static final Logger log = getLogger(CatServiceTest.class);

  @Autowired
  private CatService service;
  @Autowired
  private EntityManager entityManager;

  private LocalDateTime before;

  @Before
  public void setUp() throws Exception {
    this.before = LocalDateTime.now().withNano(0);
  }

  @Test
  public void test_add() throws Exception {
    // GIVEN
    UUID chipId = randomUUID();
    UUID deviceId = randomUUID();

    // WHEN
    Cat cat = this.service.add(chipId, deviceId);

    // THEN
    assertThat(cat)
        .isNotNull()
        .extracting(Cat::getChipId, Cat::getDeviceId, Cat::getMemo, Cat::getArchivedAt)
        .containsSequence(chipId, deviceId, null, null);
    assertThat(cat.getId())
        .isPositive();
    assertThat(cat.getCreatedAt())
        .isEqualTo(cat.getUpdatedAt())
        .isAfterOrEqualTo(this.before);
  }

  @Test
  public void test_add_twice() throws Exception {
    // GIVEN
    UUID chipId = randomUUID();
    UUID deviceId = randomUUID();
    Cat cat1 = this.service.add(chipId, deviceId);
    LocalDateTime createdAt = cat1.getCreatedAt();

    this.entityManager.flush();
    this.entityManager.clear();
    sleep(2000L);

    // WHEN
    Cat cat2 = this.service.add(chipId, deviceId);

    // THEN
    assertThat(cat2)
        .isNotNull()
        .extracting(Cat::getId, Cat::getChipId, Cat::getDeviceId, Cat::getName, Cat::getMemo,
            Cat::getArchivedAt, Cat::getCreatedAt, Cat::getUpdatedAt)
        .containsSequence(cat1.getId(), chipId, deviceId, cat1.getName(), null, null, createdAt, createdAt);
  }
}
