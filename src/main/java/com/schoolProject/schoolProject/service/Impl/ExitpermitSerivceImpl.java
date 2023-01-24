package com.schoolProject.schoolProject.service.Impl;

import com.schoolProject.schoolProject.exception.model.ExitPermitExitException;
import com.schoolProject.schoolProject.exception.model.ExitPermitNotFoundException;
import com.schoolProject.schoolProject.exception.model.IncomeNotFoundException;
import com.schoolProject.schoolProject.model.ExitPermit;
import com.schoolProject.schoolProject.model.Income;
import com.schoolProject.schoolProject.repository.ExitPermitRepository;
import com.schoolProject.schoolProject.service.ExitPermitService;
import com.schoolProject.schoolProject.service.IncomeService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.schoolProject.schoolProject.service.Impl.IncomeServiceImpl.INCOME_NOT_FOUND_BY_INCOME_ID;

@Service
@Transactional
public class ExitpermitSerivceImpl implements ExitPermitService {

    public static final String EXIT_PERMIT_ALREADY_EXITS_BY_ID = "ExitPermit Already Exits By Id: ";
    public static final String EXIT_PERMIT_NOT_FOUND_BY_EXIT_PREMIT_ID = "ExitPermit Not Found By ExitPremit Id: ";
    private final Logger LOGGER= LoggerFactory.getLogger(getClass());
    private final ExitPermitRepository exitPermitRepository;
    private final IncomeService incomeService;

    @Autowired
    public ExitpermitSerivceImpl(ExitPermitRepository exitPermitRepository, IncomeService incomeService) {
        this.exitPermitRepository = exitPermitRepository;
        this.incomeService = incomeService;
    }

    @Override
    public List<ExitPermit> listExitpermit() {return exitPermitRepository.findAll();}

    @Override
    public ExitPermit findByExitpermitId(String exitpermitId) {return exitPermitRepository.findByExitpermitId(exitpermitId);}

    @Override
    public ExitPermit findByEmpName(String empName) {return exitPermitRepository.findByEmpName(empName);}

    @Override
    public ExitPermit addNewExitpermit(ExitPermit exitPermit,String incomeId) throws ExitPermitExitException, IncomeNotFoundException {

       // ValidateNewExitpermit(exitPermit.getEmpName());

        ExitPermit NewexitPermit = new ExitPermit();
        if (StringUtils.isNotBlank(incomeId)){
            Income income = incomeService.findByIncomeId(incomeId);
            if (income !=null){
                NewexitPermit.setIncome(income);
            } else {

                throw new IncomeNotFoundException(INCOME_NOT_FOUND_BY_INCOME_ID+incomeId);
            }
        }
        NewexitPermit.setExitpermitId(generateExitPermitId());
        NewexitPermit.setPermitdate(exitPermit.getPermitdate());
        NewexitPermit.setFees(exitPermit.getFees());

        if (exitPermit.getReciptNo()==null || exitPermit.getReciptNo().equals("")){
            NewexitPermit.setReciptNo("Non");
        }else {
            NewexitPermit.setReciptNo(exitPermit.getReciptNo());
        }

        if (exitPermit.getCheckBankNo()==null||exitPermit.getCheckBankNo().equals("")){
            NewexitPermit.setCheckBankNo("Non");
        } else {
            NewexitPermit.setCheckBankNo(exitPermit.getCheckBankNo());
        }
        NewexitPermit.setEmpName(exitPermit.getEmpName());
        exitPermitRepository.save(NewexitPermit);

        Income income2 = incomeService.findByIncomeId(incomeId);
        incomeService.updateIncomestatus(income2.getIncomeId(),income2);

        return NewexitPermit;
    }

    private ExitPermit ValidateNewExitpermit(String empName) throws ExitPermitExitException {

        if (StringUtils.isNotBlank(empName)){
            ExitPermit exitPermit = findByEmpName(empName);
                if (exitPermit.getPermitdate().equals(LocalDate.now())){
                    throw new ExitPermitExitException(EXIT_PERMIT_ALREADY_EXITS_BY_ID +exitPermit.getExitpermitId());
                }
            }

        return null;
    }


    @Override
    public ExitPermit updateExitpermit(String exitpermitId, ExitPermit exitPermitDetails) throws ExitPermitNotFoundException {

        ExitPermit updateexitPermit=findByExitpermitId(exitpermitId);
        if (updateexitPermit==null){
            throw new ExitPermitNotFoundException(EXIT_PERMIT_NOT_FOUND_BY_EXIT_PREMIT_ID +exitpermitId);
        }
        updateexitPermit.setFees(exitPermitDetails.getFees());
        if (updateexitPermit.getReciptNo()==null || updateexitPermit.getReciptNo().equals("")){
            updateexitPermit.setReciptNo("Non");
        }else {
            updateexitPermit.setReciptNo(updateexitPermit.getReciptNo());
        }

        if (updateexitPermit.getCheckBankNo()==null||updateexitPermit.getCheckBankNo().equals("")){
            updateexitPermit.setCheckBankNo("Non");
        } else {
            updateexitPermit.setCheckBankNo(updateexitPermit.getCheckBankNo());
        }
        updateexitPermit.setEmpName(exitPermitDetails.getEmpName());

        return updateexitPermit;
    }

    @Override
    public void deleteExitperimt(Long id) {exitPermitRepository.deleteById(id);}


    private String generateExitPermitId() {return "EP"+ RandomStringUtils.randomNumeric(4);}


}
