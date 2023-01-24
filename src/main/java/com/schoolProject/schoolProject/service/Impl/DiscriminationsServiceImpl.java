package com.schoolProject.schoolProject.service.Impl;

import com.schoolProject.schoolProject.exception.model.DiscriminationsExistException;
import com.schoolProject.schoolProject.exception.model.DiscriminationsNotFoundException;
import com.schoolProject.schoolProject.model.Discriminations;
import com.schoolProject.schoolProject.repository.DiscriminationsRepository;
import com.schoolProject.schoolProject.service.DiscriminationsService;
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
public class DiscriminationsServiceImpl implements DiscriminationsService {

    public static final String DISCRIMINATION_NOT_FOUND_BY_COMPANY_ID = "Discrimination not Found by Discrimination Id:";

    public static final String DISCRIMINATION_ALREADY_EXISTS = "Discrimination already exists";
    private final Logger LOGGER= LoggerFactory.getLogger(getClass());
    private final DiscriminationsRepository discriminationsRepository;
    @Autowired
    public DiscriminationsServiceImpl(DiscriminationsRepository discriminationsRepository) {
        this.discriminationsRepository = discriminationsRepository;
    }

    @Override
    public List<Discriminations> discrList() {return discriminationsRepository.findAll();}

    @Override
    public Discriminations findByDicrId(String dicrId) {return discriminationsRepository.findBydicrId(dicrId);}

    @Override
    public Discriminations findByDicrName(String discrName) {
        return discriminationsRepository.findBydiscrName(discrName);
    }

    @Override
    public Discriminations addNewDiscrimation(Discriminations Newdiscriminations) throws DiscriminationsExistException {
        ValidateNewDiscrimination(Newdiscriminations.getDiscrName());
        Discriminations discriminations = new Discriminations();
        discriminations.setDicrId(generateDiscriminId());
        discriminations.setDiscrName(Newdiscriminations.getDiscrName());
        discriminationsRepository.save(discriminations);
        return discriminations;
    }



    @Override
    public Discriminations updateDiscrimation(String dicrId, Discriminations Newdiscriminations) throws DiscriminationsNotFoundException {

        Discriminations discriminations= findByDicrId(dicrId);

        if (discriminations ==null){
            throw new DiscriminationsNotFoundException(DISCRIMINATION_NOT_FOUND_BY_COMPANY_ID+dicrId.toLowerCase());
        } else {
            discriminations.setDiscrName(Newdiscriminations.getDiscrName());
            LOGGER.info(Newdiscriminations.getDiscrName());
        }
        discriminationsRepository.save(discriminations);
        return discriminations;
    }

    @Override
    public void deleteDiscriminations(Long id) {discriminationsRepository.deleteById(id);}


    private String generateDiscriminId() {return "DISC"+ RandomStringUtils.randomNumeric(5);}

    private Discriminations ValidateNewDiscrimination(String discrName) throws DiscriminationsExistException {

        if (StringUtils.isNotBlank(discrName)){
            Discriminations discriminations = findByDicrName(discrName);
            if (discriminations !=null){
                throw new DiscriminationsExistException(DISCRIMINATION_ALREADY_EXISTS);
            }
            return null;
        }
        return null;
    }
}