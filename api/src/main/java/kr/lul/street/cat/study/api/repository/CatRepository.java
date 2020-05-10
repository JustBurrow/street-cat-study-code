package kr.lul.street.cat.study.api.repository;

import kr.lul.street.cat.study.api.data.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author justburrow
 * @since 2020/05/10
 */
@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
  Cat findByChipIdAndDeviceId(UUID chipId, UUID deviceId);
}
