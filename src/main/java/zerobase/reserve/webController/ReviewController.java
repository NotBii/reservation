package zerobase.reserve.webController;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/write")
    public ResponseEntity<?> writeReview(@RequestBody ReviewEntity reviewEntity) {
        ReviewEntity result = reviewService.writeReview(reviewEntity);

        return ResponseEntity.ok(result);
    }
    //리뷰 조회()
    @GetMapping("/showlist")
    public ResponseEntity<?> reviewList(@RequestParam(required = false) String storeName, @RequestParam(required = false) String userName) {
        Page<ReviewEntity> result = reviewService.reviewLists(storeName, userName);

        return ResponseEntity.ok(result);
    }
    //리뷰 읽기

    @GetMapping("/read/{id}")
    public ResponseEntity<?> readReview(@PathVariable long id) {
        ReviewEntity result = reviewService.readReview(id);

        return ResponseEntity.ok(result);
    }
    //리뷰 수정

    @PostMapping("/update")
    public ResponseEntity<?> updateReview(@RequestBody ReviewEntity reviewEntity) {
        ReviewEntity result = reviewService.updateReview(reviewEntity);
        return ResponseEntity.ok(result);

    }

    //리뷰 삭제

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable long id) {
        Long result = this.reviewService.deleteReview(id);

        return ResponseEntity.ok(result);
    }



}
