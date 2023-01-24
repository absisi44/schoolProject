package com.schoolProject.schoolProject.service;

import com.schoolProject.schoolProject.exception.model.StockExistException;
import com.schoolProject.schoolProject.exception.model.StockNotFoundException;
import com.schoolProject.schoolProject.model.Companies;
import com.schoolProject.schoolProject.model.Discriminations;
import com.schoolProject.schoolProject.model.Fuel;
import com.schoolProject.schoolProject.model.Stock;

import java.util.List;

public interface StockService {

    List<Stock> listStock();
    Stock findByStockId(String stockId);
    Stock findStock(Companies companies, Fuel fuel, Discriminations discriminations);
    double findQuantity(Companies companies, Fuel fuel, Discriminations discriminations);
    void updateQuantity(Companies companies, Fuel fuel, Discriminations discriminations,double quantity);
    Stock addNewStock(Stock stock) throws StockExistException;
    Stock updateStock(String stockId,Stock stock) throws StockNotFoundException;
    void deleteStock(Long id);
}
