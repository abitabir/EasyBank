/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.EasyBankProject.service.budget;

import com.mthree.EasyBankProject.model.Budget;
import com.mthree.EasyBankProject.model.User;
import com.mthree.EasyBankProject.repository.BudgetRepository;
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
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Budget createBudget(Budget budget) {

        if (budget.getBudgetName() == null || budget.getBudgetName().trim().isEmpty()) {
            throw new IllegalArgumentException("Budget name cannot be null or empty.");
        }

        if (budget.getBudgetFrequency() == null) {
            throw new IllegalArgumentException("Budget frequency cannot be null.");
        }

        if (!userRepository.existsById(budget.getUser().getUserID())) {
            throw new IllegalArgumentException("User does not exist.");
        }

        return budgetRepository.save(budget);
    }

    @Override
    public Optional<Budget> getBudgetById(Long budgetId) {
        return budgetRepository.findById(budgetId);
    }

    @Override
    public List<Budget> getAllBudgetsForUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User does not exist.");
        }

        User user = userRepository.findById(userId).get();
        return budgetRepository.findByUser(user);
    }

    @Override
    public void updateBudget(Long budgetId, Budget budget) {
        if (budgetRepository.existsById(budgetId)) {
            budget.setBudgetID(budgetId);
            budgetRepository.save(budget);
        }
    }

    @Override
    public void deleteBudget(Long budgetId) {
        if (budgetRepository.existsById(budgetId)) {
            budgetRepository.deleteById(budgetId);
        }
    }

    @Override
    public List<Budget> getAllBudgetsForUserByFrequency(Long userId, Budget.BudgetFrequency frequency) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));

        return budgetRepository.findByUserAndBudgetFrequency(user, frequency);
    }

    @Override
    public List<Budget> getAllBudgetsForUserByName(Long userId, String budgetName) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));

        return budgetRepository.findByUserAndBudgetName(user, budgetName);
    }

    @Override
    public List<Budget> getAllBudgetsForUserByNameContaining(Long userId, String budgetNameContains) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));

        return budgetRepository.findByUserAndBudgetNameContainingIgnoreCase(user, budgetNameContains);
    }

}
