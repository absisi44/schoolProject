package com.schoolProject.schoolProject.service.Impl;

import com.schoolProject.schoolProject.exception.model.FuelExistException;
import com.schoolProject.schoolProject.exception.model.LocalesNotFoundException;
import com.schoolProject.schoolProject.model.Locales;
import com.schoolProject.schoolProject.repository.LocalesRepository;
import com.schoolProject.schoolProject.service.LocalService;
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
public class LocalesServiceImpl implements LocalService {

    public static final String LOCALES_NOT_FOUND_BY_COMPANY_ID = "Locale not Found by Locale Id:";
    public static final String LOCALES_ALREADY_EXISTS = "Locales already exists";

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private LocalesRepository localesRepository;

    @Autowired
    public LocalesServiceImpl(LocalesRepository localesRepository) {
        this.localesRepository = localesRepository;
    }

    @Override
    public List<Locales> localesList() {
        return localesRepository.findAll();
    }

    @Override
    public Locales findLocalById(String locaId) {
        return localesRepository.findBylocaId(locaId);
    }

    @Override
    public Locales findLocalByNmae(String locaName) {
        return localesRepository.findByLocaName(locaName);
    }

    @Override
    public Locales addNewLocales(Locales Newlocales) throws FuelExistException {

        ValidateNewLocales(Newlocales.getLocaName());
        Locales locales = new Locales();
        locales.setLocaId(generateLocID());
        locales.setLocaName(Newlocales.getLocaName());
        localesRepository.save(locales);
        return locales;
    }


    @Override
    public Locales updateLocales(String locaId, Locales uplocales) throws LocalesNotFoundException {

        Locales locales = findLocalById(locaId);

        if (locales == null) {

            throw new LocalesNotFoundException(LOCALES_NOT_FOUND_BY_COMPANY_ID + locaId);
        } else {

            locales.setLocaName(uplocales.getLocaName());
        }
        localesRepository.save(locales);
        return locales;
    }

    @Override
    public void deleteLocales(Long id) {
        localesRepository.deleteById(id);
    }


    private String generateLocID() {
        return "LO" + RandomStringUtils.randomNumeric(5);
    }

    private Locales ValidateNewLocales(String locaName) throws FuelExistException {

        if (StringUtils.isNotBlank(locaName)) {

            Locales locales = findLocalByNmae(locaName);
            if (locales != null) {
                throw new FuelExistException(LOCALES_ALREADY_EXISTS);
            }
            return null;
        }
        return null;
    }
}
