package com.schoolProject.schoolProject.service;

import com.schoolProject.schoolProject.exception.model.ExitPermitExitException;
import com.schoolProject.schoolProject.exception.model.ExitPermitNotFoundException;
import com.schoolProject.schoolProject.exception.model.IncomeNotFoundException;
import com.schoolProject.schoolProject.model.ExitPermit;

import java.util.List;

public interface ExitPermitService {
    List<ExitPermit> listExitpermit();
    ExitPermit findByExitpermitId(String exitpermitId);

    ExitPermit findByEmpName(String empName);

    ExitPermit addNewExitpermit(ExitPermit exitPermit,String incomeId) throws ExitPermitExitException, IncomeNotFoundException;
    ExitPermit updateExitpermit(String exitpermitId,ExitPermit exitPermitDetails) throws ExitPermitNotFoundException;
    void deleteExitperimt(Long id);
}
