package zerobase.reserve.webController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.reserve.model.AccountModel;
import zerobase.reserve.persist.entity.AccountEntity;
import zerobase.reserve.security.TokenProvider;
import zerobase.reserve.service.AccountService;

//회원관련

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final TokenProvider tokenProvider;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AccountModel.SignUp request) {
        AccountEntity result = this.accountService.register(request);

        return ResponseEntity.ok(result);
    }

    //로그인
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AccountModel.SignIn request) {
        AccountEntity account = this.accountService.authenticate(request);
        String token = this.tokenProvider.generateToken(account.getUsername(), account.getRole());
        return ResponseEntity.ok(token);
    }


}
