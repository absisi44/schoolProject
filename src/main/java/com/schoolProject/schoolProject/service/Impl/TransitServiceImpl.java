package com.schoolProject.schoolProject.service.Impl;

import com.schoolProject.schoolProject.exception.model.TransitExistException;
import com.schoolProject.schoolProject.exception.model.TransitNotFoundException;
import com.schoolProject.schoolProject.model.Transit;
import com.schoolProject.schoolProject.repository.TransitRepository;
import com.schoolProject.schoolProject.service.TransitService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TransitServiceImpl implements TransitService {
    public static final String TRANSIT_NOT_FOUND_BY_COMPANY_ID = "Transit not Found by Transit Id:";

    public static final String TRANSIT_ALREADY_EXISTS = "Transit already exists";

    private final TransitRepository transitRepository;

    @Autowired
    public TransitServiceImpl(TransitRepository transitRepository) {
        this.transitRepository = transitRepository;
    }

    @Override
    public List<Transit> listTransit() {return transitRepository.findAll();}

    @Override
    public Transit findByTransitId(String transitId) {return transitRepository.findByTransitId(transitId);}

    @Override
    public Transit findByIncomeDate(LocalDate incomeDate) {
        return transitRepository.findByIncomeDate(incomeDate);}

    @Override
    public Transit finadByTruckNo(String truckNo) {return transitRepository.findByTruckNo(truckNo);}

    @Override
    public Transit addNewTransit(Transit transit) throws TransitExistException {

        ValidateNewTransit(transit.getTruckNo());
        Transit newtransit = new Transit();
        newtransit.setTransitId(generateTransitId());
        newtransit.setIncomeDate(transit.getIncomeDate());
        newtransit.setTruckNo(transit.getTruckNo());
        newtransit.setCompanies(transit.getCompanies());
        newtransit.setFuel(transit.getFuel());
        newtransit.setQuantity(transit.getQuantity());
        newtransit.setShepmetDirection(transit.getShepmetDirection());
        newtransit.setAgentName(transit.getAgentName());
        transitRepository.save(newtransit);
        return newtransit;
    }



    @Override
    public Transit updateTransit(String transitId, Transit transit) throws TransitNotFoundException {

        Transit updateTransit = findByTransitId(transitId);
        if (updateTransit == null){
            throw new TransitNotFoundException(TRANSIT_NOT_FOUND_BY_COMPANY_ID+transitId);
        }
        updateTransit.setTruckNo(transit.getTruckNo());
        updateTransit.setCompanies(transit.getCompanies());
        updateTransit.setFuel(transit.getFuel());
        updateTransit.setQuantity(transit.getQuantity());
        updateTransit.setShepmetDirection(transit.getShepmetDirection());
        updateTransit.setAgentName(transit.getAgentName());
        transitRepository.save(updateTransit);
        return updateTransit;
    }

    @Override
    public void delteTransit(Long id) {transitRepository.deleteById(id);}


    private String generateTransitId() {return "TR"+ RandomStringUtils.randomNumeric(5);}

    private Transit ValidateNewTransit(String truckNo) throws TransitExistException {

        if (StringUtils.isNotBlank(truckNo)) {

            Transit transit= finadByTruckNo(truckNo);
            if (transit !=null){
                if (transit.getIncomeDate().equals(LocalDate.now())){
                    throw new TransitExistException(TRANSIT_ALREADY_EXISTS);
                }
            }

        }
        return null;
    }

}

