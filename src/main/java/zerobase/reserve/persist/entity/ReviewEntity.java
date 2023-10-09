package zerobase.reserve.persist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "review")
@Getter
@Setter
@ToString
@NoArgsConstructor

public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; //작성자이름

    private Long reservationId; //예약정보

    private String storename;

    private String reservedate;

    private String subject;

    private String content;

    private String date; // 작성일자

}
