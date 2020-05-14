package kr.lul.street.cat.study.batch.data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author justburrow
 * @since 2020/05/12
 */
@Entity(name = "Use")
@Table(name = "uses")
public class Use {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  private long id;
  @Column(name = "chip_id", nullable = false, updatable = false)
  private UUID chipId;
  @Column(name = "device_id", nullable = false, updatable = false)
  private UUID deviceId;
  @Column(name = "type", nullable = false, updatable = false)
  @Enumerated(EnumType.STRING)
  private DeviceType type;
  @Column(name = "value", nullable = false, updatable = false)
  private int value;
  @Column(name = "measured_at", nullable = false, updatable = false)
  private LocalDateTime measuredAt;
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  public Use() {// JPA only
  }

  public Use(UUID chipId, UUID deviceId, DeviceType type, int value, LocalDateTime measuredAt) {
    this.chipId = chipId;
    this.deviceId = deviceId;
    this.type = type;
    this.value = value;
    this.measuredAt = measuredAt;
  }

  @PrePersist
  private void prePersist() {
    this.createdAt = LocalDateTime.now().withNano(0);
  }

  public long getId() {
    return this.id;
  }

  public UUID getChipId() {
    return this.chipId;
  }

  public UUID getDeviceId() {
    return this.deviceId;
  }

  public DeviceType getType() {
    return this.type;
  }

  public int getValue() {
    return this.value;
  }

  public LocalDateTime getMeasuredAt() {
    return this.measuredAt;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  @Override
  public String toString() {
    return new StringBuilder(Use.class.getSimpleName())
               .append("{id=").append(this.id)
               .append(", chipId=").append(this.chipId)
               .append(", deviceId=").append(this.deviceId)
               .append(", type=").append(this.type)
               .append(", value=").append(this.value)
               .append(", measuredAt=").append(this.measuredAt)
               .append(", createdAt=").append(this.createdAt)
               .append('}').toString();
  }
}
