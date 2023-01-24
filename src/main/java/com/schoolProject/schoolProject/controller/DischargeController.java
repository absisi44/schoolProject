package com.schoolProject.schoolProject.controller;

import com.schoolProject.schoolProject.exception.ExceptionHandling;
import com.schoolProject.schoolProject.exception.model.DischargeExistException;
import com.schoolProject.schoolProject.exception.model.DischargeNotFoundException;
import com.schoolProject.schoolProject.exception.model.IncomeNotFoundException;
import com.schoolProject.schoolProject.exception.model.StockQuantityExceptionNotFound;
import com.schoolProject.schoolProject.model.Discharge;
import com.schoolProject.schoolProject.model.HttpResponse;
import com.schoolProject.schoolProject.service.DischargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path ={"/oilapp/v1/discharge/"} )
public class DischargeController extends ExceptionHandling {

    public static final String DISCHARGE_DELETE_SUCCESSFULLY = "Discharge Delete Successfully";
    private final DischargeService dischargeService;

    @Autowired
    public DischargeController(DischargeService dischargeService) {
        this.dischargeService = dischargeService;
    }

    @GetMapping("/lis-discharge")
    public ResponseEntity<List<Discharge>> getlistdischarget(){
        List<Discharge> discharge = dischargeService.listDischarge();
        return new ResponseEntity<>(discharge,OK);
    }

    @GetMapping("/find-discharge/{dischargeId}")
    public ResponseEntity<Discharge> findByDischargeId(@PathVariable("dischargeId") String dischargeId){
        Discharge discharge = dischargeService.findByDischargeId(dischargeId);
        return new ResponseEntity<>(discharge,OK);
    }

    @GetMapping("/find-discharge/{comManName}")
    public ResponseEntity<Discharge> findByComManName(@PathVariable("comManName") String comManName){
        Discharge discharge = dischargeService.findByComManName(comManName);
        return new ResponseEntity<>(discharge,OK);
    }

    @PostMapping("/add-discharge/{incomeId}")
    public ResponseEntity<Discharge> addtDischarge(@RequestBody Discharge discharge,@PathVariable("incomeId") String incomeId) throws DischargeExistException,
            IncomeNotFoundException, StockQuantityExceptionNotFound {
        Discharge NewDischarge = dischargeService.addNewDischarge(discharge,incomeId);
        return new ResponseEntity<>(NewDischarge,OK);
    }

    @PutMapping("/update-discharge/{dischargeId}")
    public ResponseEntity<Discharge> updaDischarge(@PathVariable("dischargeId") String dischargeId,@RequestBody Discharge discharge) throws DischargeNotFoundException {
        Discharge updateDischarge= dischargeService.updateDischarge(dischargeId,discharge);
        return new ResponseEntity<>(updateDischarge,OK);
    }

    @DeleteMapping("/delete-discharge/{id}")
    public ResponseEntity<HttpResponse> deletedischarge(@PathVariable("id") Long id){
        dischargeService.deleteDischarge(id);
        return response(HttpStatus.NO_CONTENT, DISCHARGE_DELETE_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<HttpResponse>(body, status);
    }

}

