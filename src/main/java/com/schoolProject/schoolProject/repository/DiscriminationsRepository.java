package com.schoolProject.schoolProject.repository;

import com.schoolProject.schoolProject.model.Discriminations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscriminationsRepository extends JpaRepository<Discriminations,Long> {

    Discriminations findBydicrId(String dicrId);
    Discriminations findBydiscrName(String discrName);

}
