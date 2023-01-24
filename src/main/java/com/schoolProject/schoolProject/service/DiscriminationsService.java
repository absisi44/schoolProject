package com.schoolProject.schoolProject.service;

import com.schoolProject.schoolProject.exception.model.DiscriminationsExistException;
import com.schoolProject.schoolProject.exception.model.DiscriminationsNotFoundException;
import com.schoolProject.schoolProject.model.Discriminations;

import java.util.List;

public interface DiscriminationsService {

    List<Discriminations> discrList();
    Discriminations findByDicrId(String dicrId);
    Discriminations  findByDicrName(String discrName);
    Discriminations addNewDiscrimation(Discriminations Newdiscriminations) throws DiscriminationsExistException;
    Discriminations updateDiscrimation(String dicrId, Discriminations Newdiscriminations ) throws DiscriminationsNotFoundException;
    void deleteDiscriminations(Long id);
}
