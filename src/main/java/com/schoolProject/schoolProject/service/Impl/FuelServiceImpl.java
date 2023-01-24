package com.schoolProject.schoolProject.service.Impl;

import com.schoolProject.schoolProject.exception.model.FuelExistException;
import com.schoolProject.schoolProject.exception.model.FuelNotFoundException;
import com.schoolProject.schoolProject.model.Fuel;
import com.schoolProject.schoolProject.repository.FuelRepository;
import com.schoolProject.schoolProject.service.FuelService;
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
public class FuelServiceImpl implements FuelService {

    public static final String FUEL_NOT_FOUND_BY_COMPANY_ID = "Fuel not Found by fuel Id:";
    public static final String FUEL_ALREADY_EXISTS = "Fuel already exists";
    private final FuelRepository fuelRepository;
    private final Logger LOGGER= LoggerFactory.getLogger(getClass());
    @Autowired
    public FuelServiceImpl(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    @Override
    public List<Fuel> getFuels() {return fuelRepository.findAll();}

    @Override
    public Fuel findByFuelId(String fuelId) {return fuelRepository.findByFuelId(fuelId);}

    @Override
    public Fuel findByFuelName(String fuelName) {return fuelRepository.findByFuelName(fuelName);}

    @Override
    public Fuel addFuel(Fuel newFuel) throws FuelExistException {

        ValidateNewFuel(newFuel.getFuelName());
        Fuel newadfuel= new Fuel();
        newadfuel.setFuelId(generateFuelId());
        newadfuel.setFuelName(newFuel.getFuelName());
        fuelRepository.save(newadfuel);
        return newadfuel;
    }


    @Override
    public Fuel updateFuel(String fuelId, Fuel fuelDetails) throws FuelNotFoundException {

        Fuel Updatefuel=findByFuelId(fuelId);
        if (Updatefuel==null){
            throw new FuelNotFoundException(FUEL_NOT_FOUND_BY_COMPANY_ID+fuelId);
        }
        Updatefuel.setFuelName(fuelDetails.getFuelName());
        return fuelRepository.save(Updatefuel);
    }

    @Override
    public void deleteFuel(Long id) {fuelRepository.deleteById(id);}

    private String generateFuelId() {return "FU"+ RandomStringUtils.randomNumeric(5);}

    private Fuel ValidateNewFuel(String fuelName) throws FuelExistException {
        if (StringUtils.isNotBlank(fuelName)){
            Fuel fuel = findByFuelName(fuelName);
            if (fuel !=null){
                throw new FuelExistException(FUEL_ALREADY_EXISTS);
            }
            return null;
        }
        return null;
    }
}
