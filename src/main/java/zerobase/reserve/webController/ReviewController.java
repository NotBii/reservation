package zerobase.reserve.webController;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zerobase.reserve.persist.entity.ReviewEntity;
import zerobase.reserve.service.ReviewService;


//리뷰관련
@RestController
@RequestMapping("/review")
@AllArgsConstructor

public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 작성(예약정보-status가 방문완료일 경우 작성가능)
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @PostMapping("/write")
    public ResponseEntity<?> writeReview(@RequestBody ReviewEntity reviewEntity) {
        ReviewEntity result = reviewService.writeReview(reviewEntity);

        return ResponseEntity.ok(result);
    }

    /**
     * storeName이 없을 시 username으로 검색, 반대의 경우 storeName으로 검색
     * 둘 다 없을 시 접근오류메세지 반환
     * @param storeName
     * @param userName
     *
     */
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN', 'SHOPMANAGER')")
    @GetMapping("/showlist")
    public ResponseEntity<?> reviewList(@RequestParam(required = false) String storename, @RequestParam(required = false) String username) {
        Page<ReviewEntity> result = reviewService.reviewLists(storename, username);

        return ResponseEntity.ok(result);
    }
    //리뷰 읽기
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN', 'SHOPMANAGER')")
    @GetMapping("/read/{id}")
    public ResponseEntity<?> readReview(@PathVariable long id) {
        ReviewEntity result = reviewService.readReview(id);

        return ResponseEntity.ok(result);
    }

    //리뷰 수정
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<?> updateReview(@RequestBody ReviewEntity reviewEntity) {
        ReviewEntity result = reviewService.updateReview(reviewEntity);
        return ResponseEntity.ok(result);

    }

    //리뷰 삭제
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable long id) {
        Long result = this.reviewService.deleteReview(id);

        return ResponseEntity.ok(result);
    }



}
