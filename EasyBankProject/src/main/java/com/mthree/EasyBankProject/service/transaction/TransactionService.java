/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.EasyBankProject.service.transaction;

import com.mthree.EasyBankProject.model.Transaction;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author khali
 */

public interface TransactionService {

    Transaction createTransaction(Transaction transaction);
    Optional<Transaction> getTransactionById(Long transactionId);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByFromAccountNumber(String accountNumber);
    List<Transaction> getTransactionsByToAccountNumber(String accountNumber);
//    void deleteTransaction(Long transactionId);

}


