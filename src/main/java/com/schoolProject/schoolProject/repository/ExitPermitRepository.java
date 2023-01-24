package com.schoolProject.schoolProject.repository;

import com.schoolProject.schoolProject.model.ExitPermit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExitPermitRepository extends JpaRepository<ExitPermit,Long> {

    ExitPermit findByExitpermitId(String exitpermitId);
    ExitPermit findByEmpName(String empName);


}