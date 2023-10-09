package zerobase.reserve.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zerobase.reserve.persist.entity.StoreEntity;
import zerobase.reserve.persist.repository.StoreRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreEntity addStore(StoreEntity storeEntity) {

        String storeName = storeEntity.getStorename();
        boolean exists = this.storeRepository.existsByStorename(storeName);
        if (exists) {
            throw new RuntimeException("이미 등록된 매장입니다 : " + storeName);
        }
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        storeEntity.setDate(time);
        this.storeRepository.save(storeEntity);

        return storeEntity;

    }

    public Page<StoreEntity> showAll() {
        Pageable limit = PageRequest.of(0, 10);
        Page<StoreEntity> storeEntities = this.storeRepository.findAll(limit);

        return storeEntities;
    }

    public Optional<StoreEntity> showStore(String storeName) {
        Optional<StoreEntity> result = this.storeRepository.findByStorename(storeName);

        if (result.isEmpty()) {
            throw new RuntimeException("존재하지 않는 매장입니다. :" + storeName);
        }

        return result;

    }

    public List<StoreEntity> searchStoreName(String keyword) {
        Pageable limit = PageRequest.of(0, 10);
        List<StoreEntity> storeEntities = this.storeRepository.findByStorenameContainsIgnoreCase(keyword, limit);

        return storeEntities;
    }

    public String deleteStore(long id) {
        StoreEntity store = this.storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("등록되지 않은 매장입니다"));
        this.storeRepository.deleteById(id);

        return store.getStorename();
    }

    public StoreEntity updateStore(StoreEntity updateEntity) {
        Long storeId = updateEntity.getId();
        StoreEntity origin = this.storeRepository.findById(storeId).get();
        origin.setDescription(updateEntity.getDescription());
        origin.setContatct(updateEntity.getContatct());
        origin.setUsername(updateEntity.getUsername());
        origin.setLocation(updateEntity.getLocation());
        storeRepository.save(origin);

        return origin;
    }
}
