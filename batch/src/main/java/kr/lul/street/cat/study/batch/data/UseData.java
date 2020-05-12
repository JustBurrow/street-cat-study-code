package kr.lul.street.cat.study.batch.data;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2020/05/12
 */
public class UseData {
  private UUID deviceId;
  private UUID chipId;
  private DeviceType type;
  private int value;
  private LocalDateTime measuredAt;

  public UseData() {
  }

  public UseData(UUID deviceId, UUID chipId, DeviceType type, int value, LocalDateTime measuredAt) {
    this.deviceId = deviceId;
    this.chipId = chipId;
    this.type = type;
    this.value = value;
    this.measuredAt = measuredAt;
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

  public DeviceType getType() {
    return this.type;
  }

  public void setType(DeviceType type) {
    this.type = type;
  }

  public int getValue() {
    return this.value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public LocalDateTime getMeasuredAt() {
    return this.measuredAt;
  }

  public void setMeasuredAt(LocalDateTime measuredAt) {
    this.measuredAt = measuredAt;
  }

  @Override
  public String toString() {
    return format("(deviceId=%s, chipId=%s, type=%s, value=%d, measuredAt=%s)",
        this.deviceId, this.chipId, this.type, this.value, this.measuredAt);
  }
}
