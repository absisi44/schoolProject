package com.schoolProject.schoolProject.service.Impl;


import com.schoolProject.schoolProject.exception.model.DischargeExistException;
import com.schoolProject.schoolProject.exception.model.DischargeNotFoundException;
import com.schoolProject.schoolProject.exception.model.IncomeNotFoundException;
import com.schoolProject.schoolProject.exception.model.StockQuantityExceptionNotFound;
import com.schoolProject.schoolProject.model.*;
import com.schoolProject.schoolProject.repository.DischargeRepository;
import com.schoolProject.schoolProject.repository.IncomeRepository;
import com.schoolProject.schoolProject.service.DischargeService;
import com.schoolProject.schoolProject.service.IncomeService;
import com.schoolProject.schoolProject.service.StockService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.schoolProject.schoolProject.service.Impl.IncomeServiceImpl.INCOME_NOT_FOUND_BY_INCOME_ID;

@Service
@Transactional
public class DischargeServiceImpl implements DischargeService {

    public static final String DISCHARGE_ALREADY_EXISTS_BY_ID = "Discharge Already Exists By Id: ";
    public static final String DISCHARGE_NOT_FOUND_BY_DISCHARGE_ID = "Discharge Not Found By discharge Id: ";
    public static final String QUANTITY_NOT_FOUND = "Quantity Not Found";
    private final DischargeRepository dischargeRepository;
    private IncomeRepository incomeRepository;
    private final StockService stockService;
    private final IncomeService incomeService;

    @Autowired
    public DischargeServiceImpl(DischargeRepository dischargeRepository, IncomeRepository incomeRepository, StockService stockService, IncomeService incomeService) {
        this.dischargeRepository = dischargeRepository;
        this.incomeRepository = incomeRepository;
        this.stockService = stockService;
        this.incomeService = incomeService;
    }

    @Override
    public List<Discharge> listDischarge() {
        return dischargeRepository.findAll();
    }

    @Override
    public Discharge findByDischargeId(String dischargeId) {
        return dischargeRepository.findByDischargeId(dischargeId);
    }

    @Override
    public Discharge findByComManName(String comManName) {
        return dischargeRepository.findByComManName(comManName);
    }

    @Override
    public Discharge addNewDischarge(Discharge discharge,String incomeId) throws DischargeExistException,
            IncomeNotFoundException, StockQuantityExceptionNotFound {

        ValidateNewAddDischarge(discharge.getComManName());


        Discharge newdischarge = new Discharge();
        if (StringUtils.isNotBlank(incomeId)){
            Income income = incomeService.findByIncomeId(incomeId);
            if (income !=null){
                newdischarge.setIncome(income);
            } else {

               throw new IncomeNotFoundException(INCOME_NOT_FOUND_BY_INCOME_ID+incomeId);
            }
        }
        newdischarge.setDischargeId(generateNewDischargeId(discharge.getDischargeId()));
        newdischarge.setDischargeDate(discharge.getDischargeDate());

        newdischarge.setEmpName(discharge.getEmpName());
        newdischarge.setComManName(discharge.getComManName());

        dischargeRepository.save(newdischarge);
        Income inStock = incomeService.findByIncomeId(incomeId);
       // Income income2 = incomeService.findByIncomeId(incomeId);
        //get quantity from stock

        double quantity =   quantity(inStock.getCompanies(), inStock.getFuel(),
                inStock.getDiscriminations());
        //add quantity from income  to quantity from stock
        double totalq = quantity + inStock.getQuantity();
        //update quantity stock
        stockService.updateQuantity(inStock.getCompanies(), inStock.getFuel(), inStock.getDiscriminations(), totalq);
        return newdischarge;
    }




    @Override
    public Discharge updateDischarge(String dischargeId, Discharge dischargeDetails) throws DischargeNotFoundException {

        Discharge updatedischarge = findByDischargeId(dischargeId);
        if (updatedischarge == null) {
            throw new DischargeNotFoundException(DISCHARGE_NOT_FOUND_BY_DISCHARGE_ID + updatedischarge.getDischargeId());
        }

        updatedischarge.setIncome(dischargeDetails.getIncome());
        updatedischarge.setEmpName(dischargeDetails.getEmpName());
        updatedischarge.setComManName(dischargeDetails.getComManName());
        dischargeRepository.save(updatedischarge);
        return updatedischarge;
    }


    @Override
    public void deleteDischarge(Long id) {
        dischargeRepository.deleteById(id);
    }

    private double quantity(Companies companies, Fuel fuel, Discriminations discriminations) throws StockQuantityExceptionNotFound {
        double stQunatity = stockService.findQuantity(companies,fuel,discriminations);
        if (stQunatity ==0){

            throw new StockQuantityExceptionNotFound(QUANTITY_NOT_FOUND);
        } else {
            return stQunatity;
        }

    }

    private String generateNewDischargeId(String dischargeId) {
        return "DIS-" + RandomStringUtils.randomNumeric(6);
    }

    private Discharge ValidateNewAddDischarge(String comManName) throws DischargeExistException {
        if (StringUtils.isNotBlank(comManName)) {
            Discharge discharge = findByComManName(comManName);
            if (discharge != null) {
                if (discharge.getDischargeDate().equals(LocalDate.now())) {
                    throw new DischargeExistException(DISCHARGE_ALREADY_EXISTS_BY_ID + discharge.getDischargeId());
                }
            }

        }
        return null;
    }
}

