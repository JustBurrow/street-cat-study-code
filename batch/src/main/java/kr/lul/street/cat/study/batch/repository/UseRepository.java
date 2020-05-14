package kr.lul.street.cat.study.batch.repository;

import kr.lul.street.cat.study.batch.data.Use;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author justburrow
 * @since 2020/05/14
 */
@Repository
public interface UseRepository extends JpaRepository<Use, Long> {
}
