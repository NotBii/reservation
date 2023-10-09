package zerobase.reserve.persist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Reservation")
@Getter
@Setter
@ToString
@NoArgsConstructor

public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; //예약자명

    private String storename; //가게이름

    private String reservedate; //예약일자

    private String status; //승인대기

    private String date; //예약신청날짜

    private String description; //예약 상태 설명




}
