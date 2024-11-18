/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.EasyBankProject.controller;

import com.mthree.EasyBankProject.model.Budget;
import com.mthree.EasyBankProject.service.budget.BudgetService;
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
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        try {
            Budget createdBudget = budgetService.createBudget(budget);
            return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long budgetId) {
        Optional<Budget> budget = budgetService.getBudgetById(budgetId);
        return budget.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Budget>> getAllBudgetsForUser(@PathVariable Long userId) {
        try {
            List<Budget> budgets = budgetService.getAllBudgetsForUser(userId);
            return ResponseEntity.ok(budgets);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{budgetId}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long budgetId, @RequestBody Budget budget) {
        try {
            budgetService.updateBudget(budgetId, budget);
            return ResponseEntity.ok(budget);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long budgetId) {
        try {
            budgetService.deleteBudget(budgetId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}/frequency/{frequency}")
    public ResponseEntity<List<Budget>> getBudgetsByFrequency(@PathVariable Long userId, @PathVariable Budget.BudgetFrequency frequency) {
        try {
            List<Budget> budgets = budgetService.getAllBudgetsForUserByFrequency(userId, frequency);
            return ResponseEntity.ok(budgets);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}/name/{budgetName}")
    public ResponseEntity<List<Budget>> getBudgetsByName(@PathVariable Long userId, @PathVariable String budgetName) {
        try {
            List<Budget> budgets = budgetService.getAllBudgetsForUserByName(userId, budgetName);
            return ResponseEntity.ok(budgets);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}/name-contains/{budgetNameContains}")
    public ResponseEntity<List<Budget>> getBudgetsByNameContaining(@PathVariable Long userId, @PathVariable String budgetNameContains) {
        try {
            List<Budget> budgets = budgetService.getAllBudgetsForUserByNameContaining(userId, budgetNameContains);
            return ResponseEntity.ok(budgets);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

