package kr.lul.street.cat.study.api.service;

import kr.lul.street.cat.study.api.data.Cat;
import kr.lul.street.cat.study.api.data.CatInfo;
import kr.lul.street.cat.study.api.repository.CatRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author justburrow
 * @since 2020/05/10
 */
@Service
public class CatService {
  private static final Logger log = getLogger(CatService.class);

  @Autowired
  private CatManagerService catManagerService;
  @Autowired
  private CatRepository repository;

  public Cat add(UUID chipId, UUID deviceId) {
    if (log.isTraceEnabled())
      log.trace("#add args : chipId={}, deviceId={}", chipId, deviceId);

    Cat cat = this.repository.findByChipIdAndDeviceId(chipId, deviceId);
    if (null == cat) {
      CatInfo catInfo = this.catManagerService.catInfo(chipId);

      cat = new Cat(chipId, deviceId, catInfo.getName());
      cat = this.repository.save(cat);
    }

    if (log.isTraceEnabled())
      log.trace("#add return : {}", cat);
    return cat;
  }
}
