package kr.lul.street.cat.study.api.rest.controller.response;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2020/05/10
 */
public class AddResponse {
  private UUID deviceId;
  private UUID chipId;
  private LocalDateTime createdAt;

  public AddResponse() {
  }

  public AddResponse(UUID deviceId, UUID chipId, LocalDateTime createdAt) {
    this.deviceId = deviceId;
    this.chipId = chipId;
    this.createdAt = createdAt;
  }

  public UUID getDeviceId() {
    return this.deviceId;
  }

  public void setDeviceId(UUID deviceId) {
    this.deviceId = deviceId;
  }

  public UUID getChipId() {
    return this.chipId;
  }

  public void setChipId(UUID chipId) {
    this.chipId = chipId;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return format("(deviceId=%s, chipId=%s, createdAt=%s)", this.deviceId, this.chipId, this.createdAt);
  }
}
