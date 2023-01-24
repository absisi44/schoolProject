package com.schoolProject.schoolProject.service.Impl;

import com.schoolProject.schoolProject.exception.model.CompanyExistException;
import com.schoolProject.schoolProject.exception.model.CompanyNotFoundException;
import com.schoolProject.schoolProject.model.Companies;
import com.schoolProject.schoolProject.repository.CompanyRepository;
import com.schoolProject.schoolProject.service.CompanyService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private final CompanyRepository repository;

    public static final String COMPANY_NOT_FOUND_BY_COMPANY_ID = "Company not Found by Company Id:";

    public static final String COMPANY_ALREADY_EXISTS = "Company already exists";


    private final Logger LOGGER= LoggerFactory.getLogger(getClass());

    @Autowired
    public CompanyServiceImpl(CompanyRepository repository) {this.repository = repository;}

    @Override
    public List<Companies> getComanies() {return repository.findAll();}

    @Override
    public Companies findCompanyByCompanyId(String companyId) {return repository.findCompanyByCompanyId(companyId);}

    @Override
    public Companies findCompanyByName(String companyName) {return repository.findCompanyByCompanyName(companyName);}

    @Override
    public Companies findById(Long id) throws CompanyNotFoundException {
        return repository.findById(id).orElseThrow(()-> new CompanyNotFoundException(COMPANY_NOT_FOUND_BY_COMPANY_ID+id));
    }

    @Override
    public Companies addNewCompany(Companies companyName) throws CompanyExistException {
        ValidateNewCompany(companyName.getCompanyName());
        Companies companies = new Companies();
        companies.setCompanyId(generateCOmpanyId());
        companies.setCompanyName(companyName.getCompanyName());
        repository.save(companies);
        return companies;
    }


    @Override
    public Companies updateCompany(String companyId,Companies companyDetails) throws CompanyNotFoundException {
        Companies company =  findCompanyByCompanyId(companyId);
        if (company == null){
            throw new CompanyNotFoundException(COMPANY_NOT_FOUND_BY_COMPANY_ID+companyId);
        }
        company.setCompanyName(companyDetails.getCompanyName());
        return repository.save(company);
    }



    @Override
    public void deleteCompany(Long id) {repository.deleteById(id);}

    private String generateCOmpanyId() {return "COM"+ RandomStringUtils.randomNumeric(5);}

    private Companies ValidateNewCompany(String nweCompanyName) throws CompanyExistException {
        if (StringUtils.isNotBlank(nweCompanyName)){
            Companies companies = findCompanyByName(nweCompanyName);
            if (companies !=null){
                throw new CompanyExistException(COMPANY_ALREADY_EXISTS);
            }
            return companies;
        }

        return null;

    }
}
