package zerobase.reserve;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import zerobase.reserve.service.AccountService;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class ReserveApplicationTests {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void passwordEncode() {
        //given
        String pw = "1111";
        //when
        String epw = passwordEncoder.encode(pw);
        //then
        System.out.println(epw);
    }

}
