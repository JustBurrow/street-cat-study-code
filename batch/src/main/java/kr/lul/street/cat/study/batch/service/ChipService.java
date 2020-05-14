package kr.lul.street.cat.study.batch.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author justburrow
 * @since 2020/05/14
 */
@Service
public class ChipService {
  public boolean validate(UUID chipId) {
    return true;
  }
}
