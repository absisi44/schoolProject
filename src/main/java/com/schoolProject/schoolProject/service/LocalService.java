package com.schoolProject.schoolProject.service;

import com.schoolProject.schoolProject.exception.model.FuelExistException;
import com.schoolProject.schoolProject.exception.model.LocalesNotFoundException;
import com.schoolProject.schoolProject.model.Locales;

import java.util.List;

public interface LocalService {
    List<Locales> localesList();
    Locales findLocalById(String locaId);
    Locales findLocalByNmae(String locaName);
    Locales addNewLocales(Locales Newlocales) throws FuelExistException;
    Locales updateLocales(String locaId, Locales uplocales) throws LocalesNotFoundException;
    void deleteLocales(Long id);
}
