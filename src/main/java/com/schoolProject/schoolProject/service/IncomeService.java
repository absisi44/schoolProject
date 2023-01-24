package com.schoolProject.schoolProject.service;

import com.schoolProject.schoolProject.exception.model.IncomeExistException;
import com.schoolProject.schoolProject.exception.model.IncomeNotFoundException;
import com.schoolProject.schoolProject.model.Income;

import java.util.List;

public interface IncomeService {
    List<Income> listIncome();
    Income findByIncomeId(String incomeId);
    Income findByTruckNo(String truckNo);
    Income addNewIncome(Income income) throws IncomeExistException;
    Income updateIncome(String incomeId,Income incomeDetails) throws IncomeNotFoundException;
    void updateIncomestatus(String incomeId,Income incomeDetails) throws IncomeNotFoundException;
    Income findByStatus(String status);

    Income findByStatus();

    void deleteIncome(Long id);
}
