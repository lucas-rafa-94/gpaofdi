package com.ofdi.soap.services.dbService;

import com.ofdi.soap.models.db.OfdiExecutionControlModel;
import com.ofdi.soap.repository.OfdiExecutionControlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfdiExecutionControlService {

    Logger logger = LoggerFactory.getLogger(OfdiExecutionControlService.class);

    @Autowired
    OfdiExecutionControlRepository ofdiExecutionControlRepository;

    public void insert(OfdiExecutionControlModel ofdiExecutionControlModel){
        try{
            ofdiExecutionControlRepository.save(ofdiExecutionControlModel);
        }catch (Exception e){
            logger.error("Erro ao se comunicar com o banco: " + e.getMessage());
        }
    }
}
