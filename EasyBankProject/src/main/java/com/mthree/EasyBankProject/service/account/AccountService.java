/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.EasyBankProject.service.account;

import com.mthree.EasyBankProject.model.Account;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author khali
 */

public interface AccountService {

    Account createAccount(Account account);
    Optional<Account> getAccountById(Long accountId);
    List<Account> getAccountsByUserId(Long userId);
    Account updateAccount(Long accountId, Account account);
    void deleteAccount(Long accountId);

}

