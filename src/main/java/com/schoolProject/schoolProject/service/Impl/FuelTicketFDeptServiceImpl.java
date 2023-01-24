package com.schoolProject.schoolProject.service.Impl;

import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptExistException;
import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptNotFoundException;
import com.schoolProject.schoolProject.model.FuelTicketFDept;
import com.schoolProject.schoolProject.repository.FuelTicketFDeptRepository;
import com.schoolProject.schoolProject.service.CompanyService;
import com.schoolProject.schoolProject.service.FuelTicketFDeptService;
import com.schoolProject.schoolProject.service.StockService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class FuelTicketFDeptServiceImpl implements FuelTicketFDeptService {


    public static final String FUEL_FROM_DEPARTMENT_NOT_FOUND_BY_ID = "Fuel From Department Not Found By Id: ";
    public static final String FUEL_FROM_DEPARTMENT_AALREADY_EXITS_BY_ID = "Fuel From Department AAlready Exits By Id: ";
    private final FuelTicketFDeptRepository ticketFDeptRepository;
    private StockService stockService;

    @Autowired
    public FuelTicketFDeptServiceImpl(FuelTicketFDeptRepository ticketFDeptRepository, StockService stockService) {
        this.ticketFDeptRepository = ticketFDeptRepository;
        this.stockService = stockService;
    }

    @Override
    public List<FuelTicketFDept> listFuelTicketFDept() {return ticketFDeptRepository.findAll();}

    @Override
    public FuelTicketFDept findByFueltiketfId(String fueltiketfId) {return ticketFDeptRepository.findByFueltiketfId(fueltiketfId);}

    @Override
    public FuelTicketFDept findFuelTicketFDept(Long companies_id, Long fuel_id, Long discriminations_id) {
        return ticketFDeptRepository.findFuelTicketFDept(companies_id,fuel_id,discriminations_id);
    }

    @Override
    public FuelTicketFDept addNewFuelTickD(FuelTicketFDept fuelTicketFDept) throws FuelTicketFDeptExistException {

        ValidateAddNewFuelTicketFDept(fuelTicketFDept.getCompanies().getId(),fuelTicketFDept.getFuel().getId(),
                fuelTicketFDept.getDiscriminations().getId());

        FuelTicketFDept newticketFDept = new FuelTicketFDept();
        newticketFDept.setFueltiketfId(generatenewticketFDeptId(fuelTicketFDept.getFueltiketfId()));
        newticketFDept.setFueltiketfDate(fuelTicketFDept.getFueltiketfDate());
        newticketFDept.setQuantity(fuelTicketFDept.getQuantity());
        newticketFDept.setBenficiary(fuelTicketFDept.getBenficiary());
        newticketFDept.setDirecation(fuelTicketFDept.getDirecation());
        newticketFDept.setCompanies(fuelTicketFDept.getCompanies());
        newticketFDept.setFuel(fuelTicketFDept.getFuel());
        newticketFDept.setDiscriminations(fuelTicketFDept.getDiscriminations());
        newticketFDept.setStatus(" لم يتم السحب");
        ticketFDeptRepository.save(newticketFDept);
        return newticketFDept;
    }



    @Override
    public FuelTicketFDept updateFuelTickD(String fueltiketfId, FuelTicketFDept fuelTicketFDept) throws FuelTicketFDeptNotFoundException {

        FuelTicketFDept updateticketFDept=findByFueltiketfId(fueltiketfId);
        if (updateticketFDept ==null){
            throw new FuelTicketFDeptNotFoundException(FUEL_FROM_DEPARTMENT_NOT_FOUND_BY_ID +fueltiketfId);
        }
        updateticketFDept.setQuantity(fuelTicketFDept.getQuantity());
        updateticketFDept.setBenficiary(fuelTicketFDept.getBenficiary());
        updateticketFDept.setDirecation(fuelTicketFDept.getDirecation());
        updateticketFDept.setCompanies(fuelTicketFDept.getCompanies());
        updateticketFDept.setFuel(fuelTicketFDept.getFuel());
        updateticketFDept.setDiscriminations(fuelTicketFDept.getDiscriminations());
        ticketFDeptRepository.save(updateticketFDept);
        return updateticketFDept;
    }

    @Override
    public FuelTicketFDept updateFuelTickDsStatus(String fueltiketfId) throws FuelTicketFDeptNotFoundException {

        FuelTicketFDept updateticketFDepts=findByFueltiketfId(fueltiketfId);
        if (updateticketFDepts ==null){
            throw new FuelTicketFDeptNotFoundException( FUEL_FROM_DEPARTMENT_NOT_FOUND_BY_ID +fueltiketfId);
        }
        updateticketFDepts.setQuantity(updateticketFDepts.getQuantity());
        updateticketFDepts.setBenficiary(updateticketFDepts.getBenficiary());
        updateticketFDepts.setDirecation(updateticketFDepts.getDirecation());
        updateticketFDepts.setCompanies(updateticketFDepts.getCompanies());
        updateticketFDepts.setFuel(updateticketFDepts.getFuel());
        updateticketFDepts.setDiscriminations(updateticketFDepts.getDiscriminations());
        updateticketFDepts.setStatus("تم السحب");
        ticketFDeptRepository.save(updateticketFDepts);
        return updateticketFDepts;
    }


    @Override
    public void deleteFuelTickD(Long id) {ticketFDeptRepository.deleteById(id);}

    private String generatenewticketFDeptId(String fueltiketfId) {return "FUTIC"+ RandomStringUtils.randomNumeric(5);}

    private FuelTicketFDept ValidateAddNewFuelTicketFDept(Long co_id, Long fu_id, Long dis_id) throws FuelTicketFDeptExistException {

        FuelTicketFDept fuelTicketFDept=findFuelTicketFDept(co_id,fu_id,dis_id);
        if (fuelTicketFDept !=null){
            if (fuelTicketFDept.getFueltiketfDate().equals(LocalDate.now())){
                throw new FuelTicketFDeptExistException( FUEL_FROM_DEPARTMENT_AALREADY_EXITS_BY_ID +fuelTicketFDept.getFueltiketfId());
            }
        }
        return null;
    }
}
