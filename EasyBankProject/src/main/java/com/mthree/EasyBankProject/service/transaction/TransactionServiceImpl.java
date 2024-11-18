/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.EasyBankProject.service.transaction;

import com.mthree.EasyBankProject.model.Transaction;
import com.mthree.EasyBankProject.repository.AccountRepository;
import com.mthree.EasyBankProject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author khali
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        if (transaction.getFromAccount() == null || transaction.getToAccount() == null 
                || !accountRepository.existsById(transaction.getFromAccount().getAccountID()) 
                || !accountRepository.existsById(transaction.getToAccount().getAccountID())) {
            throw new IllegalArgumentException("Both 'from' and 'to' accounts must be specified and both accounts must exist.");
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByFromAccountNumber(String accountNumber) {
        return transactionRepository.findByFromAccount_AccountNumber(accountNumber);
    }

    @Override
    public List<Transaction> getTransactionsByToAccountNumber(String accountNumber) {
        return transactionRepository.findByToAccount_AccountNumber(accountNumber);
    }
}