package com.schoolProject.schoolProject.controller;

import com.schoolProject.schoolProject.exception.ExceptionHandling;
import com.schoolProject.schoolProject.exception.model.IncomeExistException;
import com.schoolProject.schoolProject.exception.model.IncomeNotFoundException;
import com.schoolProject.schoolProject.model.HttpResponse;
import com.schoolProject.schoolProject.model.Income;
import com.schoolProject.schoolProject.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path ={"/oilapp/v1/income/"} )
public class IncomeController extends ExceptionHandling {

    public static final String INCOME_DELETE_SUCCESSFULLY = "Income delete Successfully";
    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/lis-income")
    public ResponseEntity<List<Income>> getlistincome(){
        List<Income> incomes = incomeService.listIncome();
        return new ResponseEntity<>(incomes,OK);
    }

    @GetMapping("/lis-incommensurate")
    public ResponseEntity<Income> getBystatus(){
        Income incomes = incomeService.findByStatus();
        return new ResponseEntity<>(incomes,OK);
    }

    @GetMapping("/find-incomeId/{incomeId}")
    public ResponseEntity<Income> getByincomeId(@PathVariable("incomeId") String incomeId){
        Income income = incomeService.findByIncomeId(incomeId);
        return new ResponseEntity<>(income,OK);
    }

    @GetMapping("/find-income/{truckNo}")
    public ResponseEntity<Income> getFuelBytruckNo(@PathVariable("truckNo") String truckNo){
        Income income = incomeService.findByTruckNo(truckNo);
        return new ResponseEntity<>(income,OK);
    }

    @PostMapping("/add-income")
    public ResponseEntity<Income> addtIncome(@RequestBody Income income) throws IncomeExistException {
        Income NewIncome = incomeService.addNewIncome(income);
        return new ResponseEntity<>(NewIncome,OK);
    }

    @PutMapping("/update-income/{incomeId}")
    public ResponseEntity<Income> updateincome(@PathVariable("incomeId") String incomeId,@RequestBody Income incomeDetails) throws IncomeNotFoundException {
        Income updateIncome= incomeService.updateIncome(incomeId,incomeDetails);
        return new ResponseEntity<>(updateIncome,OK);
    }

    @DeleteMapping("/delete-income/{id}")
    public ResponseEntity<HttpResponse> deletetransit(@PathVariable("id") Long id){
        incomeService.deleteIncome(id);
        return response(HttpStatus.NO_CONTENT, INCOME_DELETE_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<HttpResponse>(body, status);
    }
}