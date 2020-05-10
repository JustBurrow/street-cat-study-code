package kr.lul.street.cat.study.api.rest.controller.request;

import java.util.UUID;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2020/05/10
 */
public class AddRequest {
  private UUID chipId;
  private UUID deviceId;

  public AddRequest() {
  }

  public AddRequest(UUID chipId, UUID deviceId) {
    this.chipId = chipId;
    this.deviceId = deviceId;
  }

  public UUID getChipId() {
    return this.chipId;
  }

  public void setChipId(UUID chipId) {
    this.chipId = chipId;
  }

  public UUID getDeviceId() {
    return this.deviceId;
  }

  public void setDeviceId(UUID deviceId) {
    this.deviceId = deviceId;
  }

  @Override
  public String toString() {
    return format("(chipId=%s, deviceId=%s)", this.chipId, this.deviceId);
  }
}
