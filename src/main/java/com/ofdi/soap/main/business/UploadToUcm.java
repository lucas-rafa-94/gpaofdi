/* OCS - Oracle Consulting Services - Middleware
 *
 * Data de criação 03/12/2018
 * Autor: lucas.dos@oracle.com
 *
 * Projeto: GPA OFDI
 *
 *
 */
package com.ofdi.soap.main.business;

import com.ofdi.soap.main.utils.Helpers;
import com.ofdi.soap.models.db.OfdiExecutionControlModel;
import com.ofdi.soap.services.dbService.OfdiExecutionControlService;
import com.ofdi.soap.services.integrationService.UploadFileToUcmRequestClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UploadToUcm {

    Logger logger = LoggerFactory.getLogger(UploadToUcm.class);

      @Autowired
      OfdiExecutionControlService oecs;

      @Autowired
      Helpers helpers;

      @Autowired
      UploadFileToUcmRequestClient ufturc;

    public String uploadFile(String path, byte [] content, String fileName, String contentType, String documentTitle, String documentAuthor, String documentSecurityGroup, String documentAccount, String documentName) {
        String id = helpers.toTimesTamp() + fileName.replace(".zip","");
        String result = "";
        oecs.insert(new OfdiExecutionControlModel(id, fileName.trim(), path, documentAuthor, "STARTED", 0, new Date()));

        try {
            logger.info("Importando arquivo " + fileName.trim());
            result  = ufturc.uploadFileToUcmRequest(content, fileName.trim(), contentType, documentTitle, documentAuthor, documentSecurityGroup, documentAccount, documentName).getResult();
            logger.info("Sucesso ao iportar arquivo " + fileName.trim() + " id: " + result);
            oecs.insert(new OfdiExecutionControlModel(id, fileName.trim(), path, documentAuthor, "IMPORTED", 1, new Date()));
        }catch (Exception e){
            e.printStackTrace();
            logger.info("Erro ao importar Arquivo " + fileName.trim() + " : " + e.getMessage());
            oecs.insert(new OfdiExecutionControlModel(id, fileName.trim(), path, documentAuthor, "FAILED", 1, new Date()));
        }
        return result;
    }

}
