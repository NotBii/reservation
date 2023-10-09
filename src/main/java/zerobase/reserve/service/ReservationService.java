package zerobase.reserve.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.reserve.persist.entity.ReservationEntity;
import zerobase.reserve.persist.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ReservationService {

    private final ReservationRepository reservationRepository;

    //예약하기

    public ReservationEntity reserve(ReservationEntity reservationEntity) {
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        reservationEntity.setDate(time);
        this.reservationRepository.save(reservationEntity);

        return reservationEntity;
    }
    //예약전체보기 (이용자)
    //매장예약전체보기 (매니저)
    //예약상세보기

    public Optional<ReservationEntity> showDetails(long id) {
        Optional<ReservationEntity> result = this.reservationRepository.findById(Long.valueOf(id));

        if (result.isEmpty()) {
            throw new RuntimeException("존재하지 않는 예약입니다. : " + id);
        }

        return result;
    }

    //예약삭제
    public Long delete(long id) {
        ReservationEntity reservation = this.reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("예약이 존재하지 않습니다"));
        this.reservationRepository.deleteById(id);

        return reservation.getId();
    }

    //예약수정(날짜수정만 가능)
    public ReservationEntity update(ReservationEntity updateEntity) {
        Long id = updateEntity.getId();
        ReservationEntity origin = this.reservationRepository.findById(id).get();
        origin.setReservedate(updateEntity.getReservedate());
        reservationRepository.save(origin);

        return origin;
    }

    //예약상태변경 (권한이 유저일 경우 : 방문확인, 매니저일 경우 예약 승인 혹은 거절)

    public ReservationEntity updateStatus(ReservationEntity updateEntity) {
        Long id = updateEntity.getId();
        ReservationEntity origin = this.reservationRepository.findById(id).get();
        origin.setReservedate(updateEntity.getReservedate());
        reservationRepository.save(origin);


        return origin;
    }
}
