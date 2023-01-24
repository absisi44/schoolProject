package com.schoolProject.schoolProject.controller;

import com.schoolProject.schoolProject.exception.ExceptionHandling;
import com.schoolProject.schoolProject.exception.model.DiscriminationsExistException;
import com.schoolProject.schoolProject.exception.model.DiscriminationsNotFoundException;
import com.schoolProject.schoolProject.model.Discriminations;
import com.schoolProject.schoolProject.model.HttpResponse;
import com.schoolProject.schoolProject.service.DiscriminationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path ={"/oilapp/v1/discrim/"} )
public class DiscriminationsController extends ExceptionHandling {

    public static final String DISCRIMINATION_DELETE_SUCCESSFULLY = "Discrimination Delete Successfully";
    private final DiscriminationsService discriminationsService;

    @Autowired
    public DiscriminationsController(DiscriminationsService discriminationsService) {
        this.discriminationsService = discriminationsService;
    }

    @GetMapping("/discr-list")
    public ResponseEntity<List<Discriminations>> getDiscrima(){
        List<Discriminations> discrim = discriminationsService.discrList();
        return new ResponseEntity<>(discrim,OK);
    }

    @GetMapping("/discr-find/{dicrId}")
    public ResponseEntity<Discriminations> getDiscrimaById(@PathVariable("dicrId") String dicrId){
        Discriminations discrim = discriminationsService.findByDicrId(dicrId);
        return new ResponseEntity<>(discrim,OK);
    }

    @GetMapping("/discr-find-by-name/{discrName}")
    public ResponseEntity<Discriminations> getDiscrimaByName(@PathVariable("discrName") String discrName){
        Discriminations discrim = discriminationsService.findByDicrName(discrName);
        return new ResponseEntity<>(discrim,OK);
    }

    @PostMapping("/add-discr")
    public ResponseEntity<Discriminations> addNewDiscr(@RequestBody Discriminations newDiscr) throws DiscriminationsExistException {
        Discriminations newdiscrim = discriminationsService.addNewDiscrimation(newDiscr);
        return new ResponseEntity<>(newdiscrim,OK);
    }

    @PutMapping("/update-discr/{dicrId}")
    public ResponseEntity<Discriminations> updateDiscr(@PathVariable("dicrId") String dicrId,@RequestBody Discriminations discrDetailes) throws DiscriminationsNotFoundException {
        Discriminations updateLocal = discriminationsService.updateDiscrimation(dicrId,discrDetailes);
        return new ResponseEntity<>(updateLocal,OK);
    }

    @DeleteMapping("/delete-discr/{id}")
    public ResponseEntity<HttpResponse> deleteDiscr(@PathVariable("id") Long id){
        discriminationsService.deleteDiscriminations(id);
        return response(HttpStatus.NO_CONTENT, DISCRIMINATION_DELETE_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<HttpResponse>(body, status);
    }
}
