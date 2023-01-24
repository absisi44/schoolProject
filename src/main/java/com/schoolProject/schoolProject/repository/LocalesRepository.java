package com.schoolProject.schoolProject.repository;

import com.schoolProject.schoolProject.model.Locales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalesRepository extends JpaRepository<Locales,Long> {

    Locales findBylocaId(String locaId);
    Locales findByLocaName(String locaName);

}
