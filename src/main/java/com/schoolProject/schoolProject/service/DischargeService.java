package com.schoolProject.schoolProject.service;

import com.schoolProject.schoolProject.exception.model.DischargeExistException;
import com.schoolProject.schoolProject.exception.model.DischargeNotFoundException;
import com.schoolProject.schoolProject.exception.model.IncomeNotFoundException;
import com.schoolProject.schoolProject.exception.model.StockQuantityExceptionNotFound;
import com.schoolProject.schoolProject.model.Discharge;

import java.util.List;

public interface DischargeService {

    List<Discharge> listDischarge();
    Discharge findByDischargeId(String dischargeId);
    Discharge findByComManName(String comManName);
    Discharge addNewDischarge(Discharge discharge,String incomeId) throws DischargeExistException, IncomeNotFoundException, StockQuantityExceptionNotFound;
    Discharge updateDischarge(String dischargeId,Discharge discharge) throws DischargeNotFoundException;
    void deleteDischarge(Long id);
}