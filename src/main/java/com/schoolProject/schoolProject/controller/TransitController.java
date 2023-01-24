package com.schoolProject.schoolProject.controller;

import com.schoolProject.schoolProject.exception.ExceptionHandling;
import com.schoolProject.schoolProject.exception.model.TransitExistException;
import com.schoolProject.schoolProject.exception.model.TransitNotFoundException;
import com.schoolProject.schoolProject.model.HttpResponse;
import com.schoolProject.schoolProject.model.Transit;
import com.schoolProject.schoolProject.service.TransitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path ={"/oilapp/v1/transit/"} )
public class TransitController extends ExceptionHandling {

    public static final String TRANSIT_DELETE_SUCCESSFULLY = "Transit Delete Successfully";
    private final TransitService transitService;

    @Autowired
    public TransitController(TransitService transitService) {
        this.transitService = transitService;
    }

    @GetMapping("/lis-transit")
    public ResponseEntity<List<Transit>> getlistTransit(){
        List<Transit> transits = transitService.listTransit();
        return new ResponseEntity<>(transits,OK);
    }

    @GetMapping("/find-transitId/{transitId}")
    public ResponseEntity<Transit> gettransitId(@PathVariable("transitId") String transitId){
        Transit transits = transitService.findByTransitId(transitId);
        return new ResponseEntity<>(transits,OK);
    }

    @GetMapping("/find-transitDate/{incomeDate}")
    public ResponseEntity<Transit> getFuelByName(@PathVariable("incomeDate") LocalDate incomeDate){
        Transit transit = transitService.findByIncomeDate(incomeDate);
        return new ResponseEntity<>(transit,OK);
    }

    @GetMapping("/find-truckNo/{truckNo}")
    public ResponseEntity<Transit> getFuelByName(@PathVariable("truckNo") String truckNo){
        Transit transit = transitService.finadByTruckNo(truckNo);
        return new ResponseEntity<>(transit,OK);
    }


    @PostMapping("/add-transit")
    public ResponseEntity<Transit> addtransit(@RequestBody Transit transit) throws TransitExistException {
        Transit NewTransit = transitService.addNewTransit(transit);
        return new ResponseEntity<>(NewTransit,OK);
    }

    @PutMapping("/update-transit/{transitId}")
    public ResponseEntity<Transit> updatetransit(@PathVariable("transitId") String transitId,@RequestBody Transit transit) throws TransitNotFoundException {
        Transit updateTransit= transitService.updateTransit(transitId,transit);
        return new ResponseEntity<>(updateTransit,OK);
    }

    @DeleteMapping("/delete-transit/{id}")
    public ResponseEntity<HttpResponse> deletetransit(@PathVariable("id") Long id){
        transitService.delteTransit(id);
        return response(HttpStatus.NO_CONTENT, TRANSIT_DELETE_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<HttpResponse>(body, status);
    }

}
