package zerobase.reserve.persist.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.reserve.persist.entity.StoreEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    boolean existsByStorename(String storename);

    Optional <StoreEntity> findByStorename(String name);

    List<StoreEntity> findByStorenameContainsIgnoreCase(String keyword, Pageable pageable);

}
