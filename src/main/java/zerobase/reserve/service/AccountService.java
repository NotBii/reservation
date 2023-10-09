package zerobase.reserve.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.reserve.persist.repository.AccountRepository;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;



}
