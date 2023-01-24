package com.schoolProject.schoolProject.controller;

import com.schoolProject.schoolProject.exception.ExceptionHandling;
import com.schoolProject.schoolProject.exception.model.FuelExistException;
import com.schoolProject.schoolProject.exception.model.FuelNotFoundException;
import com.schoolProject.schoolProject.model.Fuel;
import com.schoolProject.schoolProject.model.HttpResponse;
import com.schoolProject.schoolProject.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path ={"/oilapp/v1/fuel/"} )
public class FuelController  extends ExceptionHandling {

    public static final String FUEL_DELETE_SUCCESSFULLY = "Fuel Delete Successfully";
    private final FuelService fuelService;

    @Autowired
    public FuelController(FuelService fuelSevice) {
        this.fuelService = fuelSevice;
    }

    @GetMapping("/lis-fuels")
    public ResponseEntity<List<Fuel>> getlistFuels(){
        List<Fuel> fuels = fuelService.getFuels();
        return new ResponseEntity<>(fuels,OK);
    }
    @GetMapping("/find-fuelId/{fuelId}")
    public ResponseEntity<Fuel> getFuelById(@PathVariable("fuelId") String fuelId){
        Fuel fuel = fuelService.findByFuelId(fuelId);
        return new ResponseEntity<>(fuel,OK);
    }

    @GetMapping("/find-fuel/{fuelName}")
    public ResponseEntity<Fuel> getFuelByName(@PathVariable("fuelName") String fuelName){
        Fuel fuel = fuelService.findByFuelName(fuelName);
        return new ResponseEntity<>(fuel,OK);
    }

    @PostMapping("/add-fuel")
    public ResponseEntity<Fuel> addFuel(@RequestBody Fuel fuel) throws FuelExistException {
        Fuel Newfuel = fuelService.addFuel(fuel);
        return new ResponseEntity<>(Newfuel,OK);
    }

    @PutMapping("/update-fuelId/{fuelId}")
    public ResponseEntity<Fuel> updateFuel(@PathVariable("fuelId") String fuelId,@RequestBody Fuel fuel) throws FuelNotFoundException {

        Fuel updatefuel= fuelService.updateFuel(fuelId,fuel);
        return new ResponseEntity<>(updatefuel,OK);
    }

    @DeleteMapping("/delete-fuel/{id}")
    public ResponseEntity<HttpResponse> deleteFuel(@PathVariable("id") Long id){
        fuelService.deleteFuel(id);
        return response(HttpStatus.NO_CONTENT, FUEL_DELETE_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<HttpResponse>(body, status);
    }

}
