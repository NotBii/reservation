package zerobase.reserve.webController;

//매장리스트 관련

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reserve.persist.entity.StoreEntity;
import zerobase.reserve.service.StoreService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
@AllArgsConstructor

public class StoreController {

    private final StoreService storeService;

    //매장추가
    @PostMapping("/add")
    public ResponseEntity<?> addStore(@RequestBody StoreEntity storeEntity) {

        StoreEntity result = storeService.addStore(storeEntity);

        return ResponseEntity.ok(result);
    }

    //매장전체보기
    @GetMapping("/showall")
    public ResponseEntity<?> showAll() {
        Page<StoreEntity> stores = this.storeService.showAll();
        return ResponseEntity.ok(stores);
    }
    //매장상세보기
    @GetMapping("/show/{storeName}")
    public ResponseEntity<?> showStore(@PathVariable String storeName) {
        Optional<StoreEntity> store = this.storeService.showStore(storeName);
        return ResponseEntity.ok(store);
    }

    //매장찾기
    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchStore(@PathVariable String keyword) {
        List<StoreEntity> stores = this.storeService.searchStoreName(keyword);
        return ResponseEntity.ok(stores);
    }

    //매장삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable long id) {
        String result = this.storeService.deleteStore(id);

        return ResponseEntity.ok(result);
    }

    //매장정보수정
    @PostMapping("/update")
    public ResponseEntity<?> updateStore(@RequestBody StoreEntity storeEntity) {
        StoreEntity result = storeService.updateStore(storeEntity);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
        return null;
    }

}
