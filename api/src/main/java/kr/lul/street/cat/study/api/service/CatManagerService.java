package kr.lul.street.cat.study.api.service;

import kr.lul.street.cat.study.api.data.CatInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 고양이 정보 관리 서비스.
 *
 * @author justburrow
 * @since 2020/05/10
 */
@Service
public class CatManagerService {
  private static final Logger log = getLogger(CatManagerService.class);

  private DigestUtils digestUtils;

  @PostConstruct
  private void postConstruct() {
    this.digestUtils = new DigestUtils("SHA3-256");
  }

  /**
   * 인식표 정보로 고양이 정보를 찾는다.
   *
   * 가짜로 인식표 ID를 이름으로 사용한 고양이 정보를 반환한다.
   *
   * @param chipId 인식표 ID.
   *
   * @return 고양이 정보. 없으면 {@code null}.
   */
  public CatInfo catInfo(UUID chipId) {
    if (log.isTraceEnabled())
      log.trace("#catInfo args : chipId={}", chipId);

    CatInfo info = new CatInfo(this.digestUtils.digestAsHex(chipId.toString()), chipId);

    if (log.isTraceEnabled())
      log.trace("#catInfo return : {}", info);
    return info;
  }
}
