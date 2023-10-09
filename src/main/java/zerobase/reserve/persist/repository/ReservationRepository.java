package zerobase.reserve.persist.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.reserve.persist.entity.ReservationEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    boolean existsById(Long reservationId);
    Page<ReservationEntity> findByUsername(Pageable limit, String userName);
    Page<ReservationEntity> findByStorename(Pageable limit, String storeName);
}
