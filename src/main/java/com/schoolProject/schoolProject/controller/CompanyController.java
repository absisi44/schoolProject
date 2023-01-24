package com.schoolProject.schoolProject.controller;

import com.schoolProject.schoolProject.exception.ExceptionHandling;
import com.schoolProject.schoolProject.exception.model.CompanyExistException;
import com.schoolProject.schoolProject.exception.model.CompanyNotFoundException;
import com.schoolProject.schoolProject.model.Companies;
import com.schoolProject.schoolProject.model.HttpResponse;
import com.schoolProject.schoolProject.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path ={"/oilapp/v1/company/"} )
public class CompanyController extends ExceptionHandling {

    private static final String COMPANY_DELETED_SUCCESSFULLY = "Company deleted successfully";
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {this.companyService = companyService;}

    @GetMapping("/listCompany")
    public ResponseEntity<List<Companies>> getCompanies(){
        List<Companies> companies= companyService.getComanies();
        return new ResponseEntity<>(companies,OK);
    }

    @GetMapping("/findCompId/{companyId}")
    public ResponseEntity<Companies> getCompanyByCompanyId(@PathVariable("companyId") String companyId){
        Companies companies = companyService.findCompanyByCompanyId(companyId);
        return new ResponseEntity<>(companies,OK);
    }

    @GetMapping("/findCompany/{companyName}")
    public ResponseEntity<Companies> getCompanyByCompanyName(@PathVariable("companyName") String companyName){
        Companies companies = companyService.findCompanyByName(companyName);
        return new ResponseEntity<>(companies,OK);
    }


    @PostMapping("/addcompany")
    public ResponseEntity<Companies> addNewCompany(@RequestBody Companies companyName) throws CompanyExistException {
        Companies newCompany=companyService.addNewCompany(companyName);
        return new ResponseEntity<>(newCompany,OK);
    }

    @PutMapping("/updateCompany/{companyId}")
    public ResponseEntity<Companies> updateCompany(@PathVariable String companyId ,@RequestBody Companies companies) throws CompanyNotFoundException {
        Companies updateCompany= companyService.updateCompany(companyId,companies);
        return new ResponseEntity<>(updateCompany,OK);
    }

    @DeleteMapping("/deleteCompany/{id}")
    public ResponseEntity<HttpResponse> deleteCompany(@PathVariable("id") Long id){
        companyService.deleteCompany(id);
        return  response(HttpStatus.NO_CONTENT,COMPANY_DELETED_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<HttpResponse>(body, status);
    }


}
