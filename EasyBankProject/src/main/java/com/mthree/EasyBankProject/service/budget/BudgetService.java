/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.EasyBankProject.service.budget;

import com.mthree.EasyBankProject.model.Budget;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author khali
 */

public interface BudgetService {

    Budget createBudget(Budget budget);
    Optional<Budget> getBudgetById(Long budgetId);
    List<Budget> getAllBudgetsForUser(Long userId);
//    List<Budget> getAllBudgets();
    void updateBudget(Long budgetId, Budget budget);
    void deleteBudget(Long budgetId);
    List<Budget> getAllBudgetsForUserByFrequency(Long userId, Budget.BudgetFrequency frequency);
    List<Budget> getAllBudgetsForUserByName(Long userId, String budgetName);
    List<Budget> getAllBudgetsForUserByNameContaining(Long userId, String budgetNameContains);

}