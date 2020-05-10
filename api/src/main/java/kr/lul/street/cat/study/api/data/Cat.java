package kr.lul.street.cat.study.api.data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author justburrow
 * @since 2020/05/10
 */
@Entity(name = "Cat")
@Table(name = "cats",
    uniqueConstraints = @UniqueConstraint(name = "uq_cats_join", columnNames = {"chip_id", "device_id"}),
    indexes = {@Index(name = "idx_cats_name", columnList = "name ASC"),
        @Index(name = "idx_cats_archived_at", columnList = "archived_at ASC")})
public class Cat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  private long id;
  @Column(name = "chip_id", nullable = false, updatable = false)
  private UUID chipId;
  @Column(name = "device_id", nullable = false, updatable = false)
  private UUID deviceId;
  @Column(name = "name", nullable = false, updatable = false)
  private String name;
  @Column(name = "memo")
  private String memo;
  @Column(name = "archived_at")
  private LocalDateTime archivedAt;
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public Cat() {
  }

  public Cat(UUID chipId, UUID deviceId, String name) {
    this.chipId = chipId;
    this.deviceId = deviceId;
    this.name = name;
  }

  public long getId() {
    return this.id;
  }

  @PrePersist
  private void prePersist() {
    this.createdAt = this.updatedAt = LocalDateTime.now().withNano(0);
  }

  @PreUpdate
  private void preUpdate() {
    this.updatedAt = LocalDateTime.now().withNano(0);
  }

  public UUID getChipId() {
    return this.chipId;
  }

  public UUID getDeviceId() {
    return this.deviceId;
  }

  public String getName() {
    return this.name;
  }

  public String getMemo() {
    return this.memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public LocalDateTime getArchivedAt() {
    return this.archivedAt;
  }

  public LocalDateTime archive() {
    this.archivedAt = LocalDateTime.now();
    return this.archivedAt;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }


  @Override
  public String toString() {
    return new StringBuilder(Cat.class.getSimpleName())
               .append("{id=").append(this.id)
               .append(", chipId=").append(this.chipId)
               .append(", deviceId=").append(this.deviceId)
               .append(", name='").append(this.name).append('\'')
               .append(", memo=").append(this.memo)
               .append(", createdAt=").append(this.createdAt)
               .append(", updatedAt=").append(this.updatedAt)
               .append('}').toString();
  }
}
