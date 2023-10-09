package zerobase.reserve.model;

import lombok.Data;
import zerobase.reserve.persist.entity.AccountEntity;


@Data
public class AccountModel {
    private String username;
    private String role;

    @Data
    public static class SignIn {
        private String username;
        private String password;
    }

    @Data
    public static class SignUp {
        private String username;
        private String password;
        private String role;

        public AccountEntity toEntity() {
            return AccountEntity.builder()
                    .username(this.username)
                    .password(this.password)
                    .role(this.role)
                    .build();
        }
    }

}
