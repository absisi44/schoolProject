package com.schoolProject.schoolProject.service;

import com.schoolProject.schoolProject.exception.model.CompanyExistException;
import com.schoolProject.schoolProject.exception.model.CompanyNotFoundException;
import com.schoolProject.schoolProject.model.Companies;
import com.schoolProject.schoolProject.model.FuelTicketFDept;

import java.util.List;

public interface CompanyService {
    List<Companies> getComanies();

    Companies findCompanyByCompanyId(String companyId);

    Companies findCompanyByName(String companyName);
    Companies findById(Long id) throws CompanyNotFoundException;

    Companies addNewCompany(Companies companyName) throws CompanyExistException;

    Companies updateCompany(String companyId ,Companies companies) throws CompanyNotFoundException;
    void deleteCompany(Long id);


}
