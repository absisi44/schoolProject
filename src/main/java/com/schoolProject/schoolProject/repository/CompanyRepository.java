package com.schoolProject.schoolProject.repository;

import com.schoolProject.schoolProject.model.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Companies,Long> {

    Companies findCompanyByCompanyId(String companyId);

    Companies findCompanyByCompanyName(String companyName);

}
