package com.schoolProject.schoolProject.service;

import com.schoolProject.schoolProject.exception.model.TransitExistException;
import com.schoolProject.schoolProject.exception.model.TransitNotFoundException;
import com.schoolProject.schoolProject.model.Transit;

import java.time.LocalDate;
import java.util.List;

public interface TransitService {

    List<Transit> listTransit();
    Transit findByTransitId(String transitId);
    Transit findByIncomeDate(LocalDate incomeDate);
    Transit finadByTruckNo(String truckNo);
    Transit addNewTransit(Transit transit) throws TransitExistException;
    Transit updateTransit(String transitId,Transit transit) throws TransitNotFoundException;
    void delteTransit(Long id);
}
