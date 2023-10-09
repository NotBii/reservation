package zerobase.reserve.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zerobase.reserve.persist.entity.ReservationEntity;
import zerobase.reserve.persist.entity.ReviewEntity;
import zerobase.reserve.persist.repository.ReservationRepository;
import zerobase.reserve.persist.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;

    //리뷰 작성(예약정보-status가 방문완료일 경우 작성가능)
    public ReviewEntity writeReview(ReviewEntity reviewEntity) {
        long reservationId = reviewEntity.getReservationId();
        ReservationEntity reservationEntity = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new RuntimeException("예약정보가 존재하지않습니다"));

        if (reviewRepository.existsByReservationId(reservationId)) {
            throw new RuntimeException("이미 리뷰를 작성했습니다");
        }

        boolean statusCheck = reservationEntity.getStatus().equals("방문확인완료");

        if (!statusCheck) {
            throw new RuntimeException("리뷰를 작성할 수 없습니다(방문미확인");
        }
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        reviewEntity.setReservedate(reservationEntity.getReservedate());
        reviewEntity.setDate(time);
        this.reviewRepository.save(reviewEntity);

        return reviewEntity;
    }

    //리뷰 리스트 조회

    public Page<ReviewEntity> reviewLists(String storeName, String userName) {
        Pageable limit = PageRequest.of(0, 10);
        Page<ReviewEntity> reviewEntities = null;

        if (storeName==null) {
            reviewEntities = this.reviewRepository.findByUsername(limit, userName);
        } else if(userName==null) {
            reviewEntities = this.reviewRepository.findByStorename(limit, storeName);
        } else {
            throw new RuntimeException("잘못된 접근입니다");
        }

        return reviewEntities;
    }

    //리뷰 읽기
    public ReviewEntity readReview(long id) {
        ReviewEntity reviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다"));

        return reviewEntity;
    }

    public ReviewEntity updateReview(ReviewEntity updateEntity) {
        long id = updateEntity.getId();
        ReviewEntity origin = reviewRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("리뷰를 찾을 수 없습니다"));


        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        origin.setSubject(updateEntity.getSubject());
        origin.setContent(updateEntity.getContent());
        origin.setDate(time + "(수정됨)");
        this.reviewRepository.save(origin);

        return origin;
    }

    //리뷰 삭제

    public Long deleteReview(long id) {
        ReviewEntity review = this.reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));
        this.reviewRepository.deleteById(id);

        return id;
    }
}
