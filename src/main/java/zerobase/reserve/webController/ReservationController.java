package zerobase.reserve.webController;

import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import zerobase.reserve.model.ReservationModel;
import zerobase.reserve.persist.entity.ReservationEntity;
import zerobase.reserve.service.ReservationService;

import java.util.Map;
import java.util.Optional;

//예약관련
@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    //예약하기
    @PostMapping("/reserve")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> reserve(@RequestBody ReservationModel reservation){


        ReservationEntity result = reservationService.reserve(reservation);

        return ResponseEntity.ok(result);

    }
    //예약전체보기 (이용자)

//    @GetMapping("/customer-reservationlist")
//    @PreAuthorize("hasRole('CUSTOMER')")
//    public ResponseEntity<?> customerShowReservation(@RequestHeader Map<String, String> data) {
////        data.get("bearer ");
////        String userName = auth.getName();
////        Page<ReservationEntity> result = reservationService.customerShowReservation(userName);
//
//        return ResponseEntity.ok(result);
//
//    }
    //매장예약전체보기 (매니저)

    //예약상세보기
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN', 'SHOPMANAGER')")
    @GetMapping("/show-details/{id}")
    public ResponseEntity<?> showDetails(@PathVariable long id) {

        Optional<ReservationEntity> result = reservationService.showDetails(id);

        return ResponseEntity.ok(result);
    }
    //예약삭제
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SHOPMANAGER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {

        Long result = this.reservationService.delete(id);

        return ResponseEntity.ok(result);
    }

    //예약수정(날짜수정만)
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<?> updateReservation(@RequestBody ReservationModel reservation) {
        ReservationEntity result = reservationService.update(reservation);

        return ResponseEntity.ok(result);

    }

    //예약상태변경

    @PreAuthorize("hasAnyRole('ADMIN', 'SHOPMANAGER')")
    @PostMapping("/update-status")
    public ResponseEntity<?> updateStatus(@RequestBody ReservationModel reservation) {
        ReservationEntity result = reservationService.updateStatus(reservation);

        return ResponseEntity.ok(result);
    }
    //예약상태변경(방문확인)

    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @PostMapping("/update-status-visited")
    public ResponseEntity<?> updateVisited(@RequestBody ReservationModel reservation) {
        ReservationEntity result = reservationService.updateStatus(reservation);
        return ResponseEntity.ok(result);
    }



}
