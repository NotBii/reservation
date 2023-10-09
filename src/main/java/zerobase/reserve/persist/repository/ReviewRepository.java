package zerobase.reserve.persist.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.reserve.persist.entity.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    boolean existsByReservationId(long reservationId);
    Page<ReviewEntity> findByUsername(Pageable limit, String username);
    Page<ReviewEntity> findByStorename(Pageable limit, String storename);
}
