/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.EasyBankProject.service.account;

import com.mthree.EasyBankProject.model.Account;
import com.mthree.EasyBankProject.model.Account.AccountType;
import com.mthree.EasyBankProject.model.User;
import com.mthree.EasyBankProject.repository.AccountRepository;
import com.mthree.EasyBankProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author khali
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Account createAccount(Account account) {

        if (account.getAccountNumber() == null || account.getAccountNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty.");
        }

        if (accountRepository.existsByAccountNumber(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number is already taken");
        }
        
        if (account.getAccountType() == null || 
            (!account.getAccountType().equals(AccountType.CURRENT) && 
             !account.getAccountType().equals(AccountType.SAVINGS))) {
            throw new IllegalArgumentException("Invalid account type. Allowed types are 'CURRENT' or 'SAVINGS'.");
        }

        if (account.getBalance() == null || account.getBalance() < 0) {
            throw new IllegalArgumentException("Starting balance cannot be null or negative.");
        }

        if (account.getUser() == null || !userRepository.existsById(account.getUser().getUserID())) {
            throw new IllegalArgumentException("User does not exist.");
        }
        
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID.");
        }
        
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User does not exist.");
        }

        User user = userRepository.findById(userId).get();
        
        return accountRepository.findByUser(user);
    }

    @Override
    public Account updateAccount(Long accountId, Account account) {
        if (!accountRepository.existsById(accountId)) {
            throw new IllegalArgumentException("Account with ID " + accountId + " does not exist.");
        }

        Account existingAccount = accountRepository.findById(accountId).get();
        
        if (account.getAccountNumber() != null && !existingAccount.getAccountNumber().equals(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number cannot be changed.");
        }

        if (account.getBalance() != null && account.getBalance() < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }

        if (account.getBalance() != null) {
            existingAccount.setBalance(account.getBalance());
        }

        return accountRepository.save(existingAccount);
    }

    @Override
    public void deleteAccount(Long accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
        } else {
            throw new IllegalArgumentException("Account with ID " + accountId + " does not exist.");
        }
    }
}
