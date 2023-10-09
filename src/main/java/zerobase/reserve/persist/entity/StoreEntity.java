package zerobase.reserve.persist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "STORE")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storename; //가게이름

    private String location; //위치

    private String contatct; //연락처

    private String description; //설명

    private String username; // 매니저 id

    private String date; // 등록일자

}
