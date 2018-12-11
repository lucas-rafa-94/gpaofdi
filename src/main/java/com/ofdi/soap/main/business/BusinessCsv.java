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
import com.ofdi.soap.services.mktImport.GetImportActivityStatusClient;
import com.ofdi.soap.services.mktImport.SubmitImportActivityRequestClient;
import mktImport.wsdl.SubmitImportActivityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class BusinessCsv {

    Logger logger = LoggerFactory.getLogger(BusinessCsv.class);

    @Autowired
    SubmitImportActivityRequestClient siarc;

    @Autowired
    GetImportActivityStatusClient giasc;

    @Autowired
    OfdiExecutionControlService oecs;

    @Autowired
    Helpers helpers;

    public void importArchive(String csv, String path, String username, boolean chefe){

        SubmitImportActivityResponse submitImportActivityResponse = new SubmitImportActivityResponse();

        String id = helpers.toTimesTamp() + csv.replace(".csv","");
        oecs.insert(new OfdiExecutionControlModel(id, csv, path, username, "STARTED", 0, new Date()));
        try{
            submitImportActivityResponse = siarc.submitImportActivity(helpers.getFileContentCsv(path + csv), chefe);
            oecs.insert(new OfdiExecutionControlModel(id, csv, path, username, "IMPORTED", 1, new Date()));
            logger.info("Sucesso ao importar arquivo  : " + csv + " id: " + submitImportActivityResponse.getResult().getJobId().getValue());
        }catch (Exception e){
            logger.error("Erro ao importar " + csv + " : " + e.getMessage());
            oecs.insert(new OfdiExecutionControlModel(id, csv, path, username, "FAILED", 1, new Date()));
            helpers.moveArchive(csv,false);
        }
        try{
            if(giasc.getImportActivityStatus(submitImportActivityResponse.getResult().getJobId().getValue()).getResult().getStatus().getValue().equals("COMPLETE_WITH_ERRORS")){
                logger.error("Erro ao importar " + csv + " : " + "COMPLETE_WITH_ERRORS");
                logger.info("Encerrando Aplicacao...");
                helpers.moveArchive(csv,false);
                System.exit(0);
            };
            oecs.insert(new OfdiExecutionControlModel(id, csv, path, username, "PROCESSED", 2, new Date()));
            helpers.moveArchive(csv,true);
        }catch (Exception e){
            logger.error("Erro ao importar " + csv + " : " + e.getMessage());
            oecs.insert(new OfdiExecutionControlModel(id, csv, path, username, "FAILED", 2, new Date()));
            helpers.moveArchive(csv,false);
        }
    }
}
