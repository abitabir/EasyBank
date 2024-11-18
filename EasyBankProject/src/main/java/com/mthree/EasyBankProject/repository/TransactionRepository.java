/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.EasyBankProject.repository;

import com.mthree.EasyBankProject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 *
 * @author khali
 */

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByFromAccount_AccountNumber(String accountNumber);

    List<Transaction> findByToAccount_AccountNumber(String accountNumber);

    List<Transaction> findByAmountGreaterThan(Double amount);

    // Custom query for finding transactions where the transferReference contains a keyword (for budget insights)
    List<Transaction> findByTransferReferenceContainingIgnoreCase(String keyword);

}
