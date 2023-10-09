package zerobase.reserve.persist.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Account")

public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; //유저명

    private String password; //비밀번호

    private String roles; //역할 : 점장, 사용자, 관리자


}
