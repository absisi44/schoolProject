package com.schoolProject.schoolProject.service.Impl;

import com.schoolProject.schoolProject.exception.model.StockExistException;
import com.schoolProject.schoolProject.exception.model.StockNotFoundException;
import com.schoolProject.schoolProject.model.Companies;
import com.schoolProject.schoolProject.model.Discriminations;
import com.schoolProject.schoolProject.model.Fuel;
import com.schoolProject.schoolProject.model.Stock;
import com.schoolProject.schoolProject.repository.StockRepository;
import com.schoolProject.schoolProject.service.StockService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    public static final String STOCK_ALREADY_EXISTS_BY_ID = "Stock Already Exists By Id: ";
    public static final String STOCK_NOT_FOUND_BY_STOCK_ID = "Stock Not Found By Stock Id: ";
    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stock> listStock() {return stockRepository.findAll();}

    @Override
    public Stock findByStockId(String stockId) {return stockRepository.findByStockId(stockId);}

    @Override
    public Stock findStock(Companies companies, Fuel fuel, Discriminations discriminations) {
        return stockRepository.findStock(companies,fuel,discriminations);
    }

    @Override
    public double findQuantity(Companies companies, Fuel fuel, Discriminations discriminations) {
        return stockRepository.getQuantity(companies,fuel,discriminations);
    }

    @Override
    public void updateQuantity(Companies companies, Fuel fuel, Discriminations discriminations, double quantity) {
        stockRepository.updateQuantity(companies,fuel,discriminations,quantity);
    }


    @Override
    public Stock addNewStock(Stock stock) throws StockExistException {
        ValidateNewaddStock(stock.getCompanies(),stock.getFuel(),stock.getDiscriminations());
        Stock Newstock = new Stock();
        Newstock.setStockId(generateStockId(stock.getStockId()));
        Newstock.setQuantity(stock.getQuantity());
        Newstock.setCompanies(stock.getCompanies());
        Newstock.setFuel(stock.getFuel());
        Newstock.setDiscriminations(stock.getDiscriminations());
        stockRepository.save(Newstock);
        return Newstock;
    }



    @Override
    public Stock updateStock(String stockId, Stock stockDetails) throws StockNotFoundException {

        Stock updatestock = findByStockId(stockId);
        if (updatestock ==null){
            throw new StockNotFoundException(STOCK_NOT_FOUND_BY_STOCK_ID +stockId);
        }
        updatestock.setQuantity(stockDetails.getQuantity());
        updatestock.setCompanies(stockDetails.getCompanies());
        updatestock.setFuel(stockDetails.getFuel());
        updatestock.setDiscriminations(stockDetails.getDiscriminations());
        stockRepository.save(updatestock);
        return updatestock;
    }

    @Override
    public void deleteStock(Long id) {stockRepository.deleteById(id);}



    private String generateStockId(String stockId) {return "STO-"+ RandomStringUtils.randomNumeric(6);}

    private Stock ValidateNewaddStock(Companies companies, Fuel fuel, Discriminations discriminations) throws StockExistException {
        Stock stock = findStock(companies,fuel,discriminations);
        if (stock !=null){

            throw new StockExistException(STOCK_ALREADY_EXISTS_BY_ID +stock.getStockId());
        }

        return null;
    }
}

