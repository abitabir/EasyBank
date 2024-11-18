/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.EasyBankProject.controller;

import com.mthree.EasyBankProject.model.Transaction;
import com.mthree.EasyBankProject.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author khali
 */

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction createdTransaction = transactionService.createTransaction(transaction);
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long transactionId) {
        Optional<Transaction> transaction = transactionService.getTransactionById(transactionId);
        return transaction.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/from/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByFromAccountNumber(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByFromAccountNumber(accountNumber);
        return transactions.isEmpty() ? 
            new ResponseEntity<>(HttpStatus.NOT_FOUND) : 
            ResponseEntity.ok(transactions);
    }

    @GetMapping("/to/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByToAccountNumber(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByToAccountNumber(accountNumber);
        return transactions.isEmpty() ? 
            new ResponseEntity<>(HttpStatus.NOT_FOUND) : 
            ResponseEntity.ok(transactions);
    }
}

