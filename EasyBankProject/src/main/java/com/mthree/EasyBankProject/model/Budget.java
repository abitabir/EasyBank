/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.EasyBankProject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author khali
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long budgetID;

    @Column(unique = true, nullable = false)
    private String budgetName;

    public enum BudgetFrequency {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY,
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BudgetFrequency budgetFrequency;

    @Column(nullable = false)
    private Double budgetLimit;

    @Column(nullable = false)
    private Double spent;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;
    
}
