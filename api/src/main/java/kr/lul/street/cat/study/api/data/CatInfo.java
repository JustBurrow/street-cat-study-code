package kr.lul.street.cat.study.api.data;

import java.util.UUID;

import static java.lang.String.format;

/**
 * 고양이 정보 관리 시스템이 제공하는 고양이 정보.
 *
 * @author justburrow
 * @since 2020/05/10
 */
public class CatInfo {
  String name;
  UUID chipId;

  public CatInfo(String name, UUID chipId) {
    this.name = name;
    this.chipId = chipId;
  }

  public String getName() {
    return this.name;
  }

  public UUID getChipId() {
    return this.chipId;
  }

  @Override
  public String toString() {
    return format("(name='%s', chipId=%s)", this.name, this.chipId);
  }
}
