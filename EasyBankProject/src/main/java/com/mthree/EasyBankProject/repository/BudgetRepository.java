/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.EasyBankProject.repository;

import com.mthree.EasyBankProject.model.Budget;
import com.mthree.EasyBankProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 *
 * @author khali
 */

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByUser(User user);
    
    List<Budget> findByUserAndBudgetFrequency(User user, Budget.BudgetFrequency budgetFrequency);

    List<Budget> findByUserAndBudgetName(User user, String budgetName);

    List<Budget> findByUserAndBudgetNameContainingIgnoreCase(User user, String budgetName);
}
