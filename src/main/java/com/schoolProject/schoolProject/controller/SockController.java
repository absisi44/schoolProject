package com.schoolProject.schoolProject.controller;

import com.schoolProject.schoolProject.exception.ExceptionHandling;
import com.schoolProject.schoolProject.exception.model.StockExistException;
import com.schoolProject.schoolProject.exception.model.StockNotFoundException;
import com.schoolProject.schoolProject.model.HttpResponse;
import com.schoolProject.schoolProject.model.Stock;
import com.schoolProject.schoolProject.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path ={"/oilapp/v1/stock/"} )
public class SockController extends ExceptionHandling {

    public static final String STOCK_DELETED_SUCCESSFULLY = "Stock Deleted Successfully";
    private final StockService stockService;

    @Autowired
    public SockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/list-stock")
    public ResponseEntity<List<Stock>> getStoks(){
        List<Stock> stockstock= stockService.listStock();
        return new ResponseEntity<>(stockstock,OK);
    }

    @PostMapping("/add-stock")
    public ResponseEntity<Stock> addNewCompany(@RequestBody Stock newstock) throws StockExistException {
        Stock newStock=stockService.addNewStock(newstock);
        return new ResponseEntity<>(newStock,OK);
    }

    @PutMapping("/update-stock/{stockId}")
    public ResponseEntity<Stock> updateCompany(@PathVariable String stockId ,@RequestBody Stock nestockDitails) throws StockNotFoundException {
        Stock updateStock= stockService.updateStock(stockId,nestockDitails);
        return new ResponseEntity<>(updateStock,OK);
    }

    @DeleteMapping("/delete-stock/{id}")
    public ResponseEntity<HttpResponse> deleteCompany(@PathVariable("id") Long id){
        stockService.deleteStock(id);
        return  response(HttpStatus.NO_CONTENT, STOCK_DELETED_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<HttpResponse>(body, status);
    }


}
