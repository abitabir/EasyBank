/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.EasyBankProject.repository;

import com.mthree.EasyBankProject.model.Account;
import com.mthree.EasyBankProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 *
 * @author khali
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUser(User user);

    boolean existsByAccountNumber(String accountNumber);

//    List<Account> findByBalanceGreaterThan(Double balance);

}
