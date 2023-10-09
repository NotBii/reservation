package zerobase.reserve.webController;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reserve.persist.entity.ReservationEntity;
import zerobase.reserve.service.ReservationService;

import java.util.Optional;

//예약관련
@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    //예약하기
    @PostMapping("/reserve")
    public ResponseEntity<?> reserve(@RequestBody ReservationEntity reservationEntity) {
        ReservationEntity result = reservationService.reserve(reservationEntity);

        return ResponseEntity.ok(result);

    }
    //예약전체보기 (이용자)
//
//    @GetMapping("/showall")
//    public ResponseEntity<?> showall() {
//
//    }
    //매장예약전체보기 (매니저)

    //예약상세보기
    @GetMapping("/showDetails/{id}")
    public ResponseEntity<?> showDetails(@PathVariable long id) {
        Optional<ReservationEntity> result = reservationService.showDetails(id);

        return ResponseEntity.ok(result);
    }
    //예약삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {

        Long result = this.reservationService.delete(id);

        return ResponseEntity.ok(result);
    }

    //예약수정(날짜수정만)
    @PostMapping("/update")
    public ResponseEntity<?> updateReservation(@RequestBody ReservationEntity reservationEntity) {
        ReservationEntity result = reservationService.update(reservationEntity);

        return ResponseEntity.ok(result);

    }

    //예약상태변경

    @PostMapping("/changeStatus")
    public ResponseEntity<?> updateStatus(@RequestBody ReservationEntity reservationEntity) {
        ReservationEntity result = reservationService.updateStatus(reservationEntity);

        return ResponseEntity.ok(result);
    }


}
