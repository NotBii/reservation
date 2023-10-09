package zerobase.reserve.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.reserve.model.AccountModel;
import zerobase.reserve.persist.entity.AccountEntity;
import zerobase.reserve.persist.repository.AccountRepository;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.accountRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수 없습니다 -> " + username));

    }

    //회원등록
    public AccountEntity register(AccountModel.SignUp account) {
        boolean exists =  this.accountRepository.existsByUsername(account.getUsername());
        if (exists) {
            throw new RuntimeException("이미 사용중인 아이디 입니다");
        }
        account.setPassword(this.passwordEncoder.encode(account.getPassword()));
        AccountEntity result = this.accountRepository.save(account.toEntity());

        return result;
    }

    //로그인
    public AccountEntity authenticate(AccountModel.SignIn account) {

        AccountEntity user = this.accountRepository.findByUsername(account.getUsername())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다"));

        if (!this.passwordEncoder.matches(account.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }

        return user;
    }
}
