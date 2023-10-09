package zerobase.reserve.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zerobase.reserve.model.ReservationModel;
import zerobase.reserve.persist.entity.ReservationEntity;
import zerobase.reserve.persist.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final Pageable LIMIT = PageRequest.of(0, 10);

    //예약하기

    public ReservationEntity reserve(ReservationModel reservation) {
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        reservation.setDate(time);
        ReservationEntity result = this.reservationRepository.save(reservation.toEntity());

        return result;
    }
    //예약전체보기 (이용자)

    public Page<ReservationEntity> customerShowReservation(String userName) {
        Page<ReservationEntity> result = this.reservationRepository.findByUsername(LIMIT, userName);

        return result;
    }
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

    //예약수정
    public ReservationEntity update(ReservationModel reservation) {
        ReservationEntity result = reservation.toEntity();

        reservationRepository.save(result);

        return result;
    }

    //예약상태변경 (front 단에서 바뀌는 부분(시간, status등) 제어해줘야함... 보완필요)
    public ReservationEntity updateStatus(ReservationModel reservation) {

        ReservationEntity result = reservation.toEntity();
        reservationRepository.save(result);

        return result;
    }
}
