package com.schoolProject.schoolProject.controller;

import com.schoolProject.schoolProject.exception.ExceptionHandling;
import com.schoolProject.schoolProject.exception.model.ExitPermitExitException;
import com.schoolProject.schoolProject.exception.model.ExitPermitNotFoundException;
import com.schoolProject.schoolProject.exception.model.IncomeNotFoundException;
import com.schoolProject.schoolProject.model.ExitPermit;
import com.schoolProject.schoolProject.model.HttpResponse;
import com.schoolProject.schoolProject.service.ExitPermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping(path ={"/oilapp/v1/exitpermit/"} )
public class ExitPermitController extends ExceptionHandling {

    public static final String EXIT_PERMIT_DELETE_SUCCESSFULLY = "ExitPermit Delete Successfully";
    ExitPermitService exitPermitService;

    @Autowired
    public ExitPermitController(ExitPermitService exitPermitService) {
        this.exitPermitService = exitPermitService;
    }

    @GetMapping("/lis-exitpermit")
    public ResponseEntity<List<ExitPermit>> getlistexitpermit(){
        List<ExitPermit> exitPermit = exitPermitService.listExitpermit();
        return new ResponseEntity<>(exitPermit,OK);
    }

    @GetMapping("/lis-exitpermit/{exitpermitId}")
    public ResponseEntity<ExitPermit> getlistexitpermit(@PathVariable("exitpermitId") String exitpermitId){
        ExitPermit exitPermit = exitPermitService.findByExitpermitId(exitpermitId);
        return new ResponseEntity<>(exitPermit,OK);
    }

    @PostMapping("/add-exitpermit/{incomeId}")
    public ResponseEntity<ExitPermit> addtIncome(@RequestBody ExitPermit exitPermit,@PathVariable("incomeId") String incomeId) throws IncomeNotFoundException, ExitPermitExitException {
        ExitPermit Newexitpermit = exitPermitService.addNewExitpermit(exitPermit,incomeId);
        return new ResponseEntity<>(Newexitpermit,OK);
    }

    @PutMapping("/update-exitpermit/{exitpermitId}")
    public ResponseEntity<ExitPermit> updateincome(@PathVariable("exitpermitId") String exitpermitId,@RequestBody ExitPermit exitpermitDetails)
            throws ExitPermitNotFoundException {
        ExitPermit updateIncome= exitPermitService.updateExitpermit(exitpermitId,exitpermitDetails);
        return new ResponseEntity<>(updateIncome,OK);
    }

    @DeleteMapping("/delete-exitpermit/{id}")
    public ResponseEntity<HttpResponse> deleteexitpermit(@PathVariable("id") Long id){
        exitPermitService.deleteExitperimt(id);
        return response(HttpStatus.NO_CONTENT, EXIT_PERMIT_DELETE_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<HttpResponse>(body, status);
    }
}
