package com.account.businesslogic;

import com.account.entity.Account;
import com.account.exception.AccountException;
import com.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import static com.account.utils.AccountConst.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountBusinessLogic {

    private final AccountRepository accountRepository;

    public Account retrieveAccount(String login, String password) throws AccountException {
        try {
            return accountRepository.retrieveAccount(login, password);
        } catch (DataAccessException dae) {
            throw new AccountException(RETRIEVE_ERROR);
        }
    }

    public Account persistAccount(Account account) throws AccountException {
        try {
            accountRepository.save(account);
            return account;
        } catch (DataAccessException dae) {
            throw new AccountException(PERSIST_ERROR);
        }
    }

    public void deleteAccount(Integer id) throws AccountException {
        try {
            accountRepository.deleteById(id);
        } catch (DataAccessException dae) {
            throw new AccountException(DELETE_ERROR);
        }
    }
}