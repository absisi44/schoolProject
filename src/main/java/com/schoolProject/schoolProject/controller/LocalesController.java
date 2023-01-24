package com.schoolProject.schoolProject.controller;

import com.schoolProject.schoolProject.exception.ExceptionHandling;
import com.schoolProject.schoolProject.exception.model.FuelExistException;
import com.schoolProject.schoolProject.exception.model.LocalesNotFoundException;
import com.schoolProject.schoolProject.model.HttpResponse;
import com.schoolProject.schoolProject.model.Locales;
import com.schoolProject.schoolProject.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path ={"/oilapp/v1/locales/"} )
public class LocalesController extends ExceptionHandling {

    public static final String LOCAL_DELETE_SUCCESSFULLY = "Local Delete successfully";
    private final LocalService localService;

    @Autowired
    public LocalesController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping("/locale-list")
    public ResponseEntity<List<Locales>> getLocales(){
        List<Locales> locales = localService.localesList();
        return new ResponseEntity<>(locales,OK);
    }

    @GetMapping("/locale-findBylocaId/{locaId}")
    public ResponseEntity<Locales> getloclaById(@PathVariable("locaId") String locaId){
        Locales locales = localService.findLocalById(locaId);
        return new ResponseEntity<>(locales,OK);
    }

    @GetMapping("/locale-findBy-name/{locaName}")
    public ResponseEntity<Locales> getloclaByName(@PathVariable("locaName") String locaName){
        Locales locales = localService.findLocalByNmae(locaName);
        return new ResponseEntity<>(locales,OK);
    }

    @PostMapping("/add-locales")
    public ResponseEntity<Locales> addNewLocales(@RequestBody Locales newlocales) throws FuelExistException {
        Locales newLocal = localService.addNewLocales(newlocales);
        return new ResponseEntity<>(newLocal,OK);
    }

    @PutMapping("/update-local/{locaId}")
    public ResponseEntity<Locales> updateLocales(@PathVariable("locaId") String locaId,@RequestBody Locales localeDetailes) throws LocalesNotFoundException {
        Locales updateLocal = localService.updateLocales(locaId,localeDetailes);
        return new ResponseEntity<>(updateLocal,OK);
    }


    @DeleteMapping("/delete-local/{id}")
    public ResponseEntity<HttpResponse> deletelocal(@PathVariable("id") Long id){
        localService.deleteLocales(id);
        return response(HttpStatus.NO_CONTENT, LOCAL_DELETE_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<HttpResponse>(body, status);
    }


}
