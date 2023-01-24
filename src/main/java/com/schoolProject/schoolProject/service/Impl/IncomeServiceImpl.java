package com.schoolProject.schoolProject.service.Impl;

import com.schoolProject.schoolProject.exception.model.IncomeExistException;
import com.schoolProject.schoolProject.exception.model.IncomeNotFoundException;
import com.schoolProject.schoolProject.model.Income;
import com.schoolProject.schoolProject.repository.IncomeRepository;
import com.schoolProject.schoolProject.service.IncomeService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class IncomeServiceImpl implements IncomeService {
    public static final String INCOME_NOT_FOUND_BY_INCOME_ID = "Income not Found by Income Id:";
    public static final String INCOME_ALREADY_EXISTS = "Income already exists  by Income Id:";

    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository) {this.incomeRepository = incomeRepository;}

    @Override
    public List<Income> listIncome() {return incomeRepository.findAll();}

    @Override
    public Income findByIncomeId(String incomeId) {return incomeRepository.findByIncomeId(incomeId);}

    @Override
    public Income findByTruckNo(String truckNo) {return incomeRepository.findByTruckNo(truckNo);}

    @Override
    public Income addNewIncome(Income income) throws IncomeExistException {
        ValidateNewIncome(income.getTruckNo());
        Income newIncome = new Income();
        newIncome.setIncomeId(generateIncomeId());
        newIncome.setIncomeDate(income.getIncomeDate());
        newIncome.setTruckNo(income.getTruckNo());
        newIncome.setInvoiceNo(income.getInvoiceNo());
        newIncome.setQuantity(income.getQuantity());
        newIncome.setShippingDate(income.getShippingDate());
        newIncome.setCompanies(income.getCompanies());
        newIncome.setFuel(income.getFuel());
        newIncome.setDiscriminations(income.getDiscriminations());
        newIncome.setLocales(income.getLocales());
        newIncome.setStatus("NOT PAID");
        incomeRepository.save(newIncome);
        return newIncome;
    }




    @Override
    public Income updateIncome(String incomeId, Income incomeDetails) throws IncomeNotFoundException {

        Income updateincome = findByIncomeId(incomeId);
        if (updateincome ==null){
            throw new IncomeNotFoundException(INCOME_NOT_FOUND_BY_INCOME_ID+incomeId);
        }
        updateincome.setTruckNo(incomeDetails.getTruckNo());
        updateincome.setInvoiceNo(incomeDetails.getInvoiceNo());
        updateincome.setQuantity(incomeDetails.getQuantity());
        updateincome.setShippingDate(incomeDetails.getShippingDate());
        updateincome.setCompanies(incomeDetails.getCompanies());
        updateincome.setFuel(incomeDetails.getFuel());
        updateincome.setDiscriminations(incomeDetails.getDiscriminations());
        updateincome.setLocales(incomeDetails.getLocales());
        incomeRepository.save(updateincome);
        return updateincome;
    }

    @Override
    public void updateIncomestatus(String incomeId, Income incomeDetails) throws IncomeNotFoundException {
        Income updateincome = findByIncomeId(incomeId);
        if (updateincome ==null){
            throw new IncomeNotFoundException(INCOME_NOT_FOUND_BY_INCOME_ID+incomeId);
        }
        updateincome.setTruckNo(incomeDetails.getTruckNo());
        updateincome.setInvoiceNo(incomeDetails.getInvoiceNo());
        updateincome.setQuantity(incomeDetails.getQuantity());
        updateincome.setShippingDate(incomeDetails.getShippingDate());
        updateincome.setCompanies(incomeDetails.getCompanies());
        updateincome.setFuel(incomeDetails.getFuel());
        updateincome.setDiscriminations(incomeDetails.getDiscriminations());
        updateincome.setLocales(incomeDetails.getLocales());
        updateincome.setStatus("PAID IS DONE");
        incomeRepository.save(updateincome);
    }

   private final String status="NOT PAID";
    @Override
    public Income findByStatus(String status) {

        status= this.status;
        return incomeRepository.findByStatus("status");
    }

    @Override
    public Income findByStatus() {return
            incomeRepository.findByStatus("NOT PAID");
    }


    @Override
    public void deleteIncome(Long id) {incomeRepository.deleteById(id);}


    private String generateIncomeId() {return "IN"+ RandomStringUtils.randomNumeric(4);}

    private Income ValidateNewIncome(String truckNo) throws IncomeExistException {

        if (StringUtils.isNotBlank(truckNo)) {

            Income income= findByTruckNo(truckNo);
            if (income !=null){
                if (income.getIncomeDate().equals(LocalDate.now())){
                    throw new IncomeExistException(INCOME_ALREADY_EXISTS+income.getIncomeId());
                }
            }

        }
        return null;
    }
}