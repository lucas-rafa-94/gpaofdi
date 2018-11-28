package com.ofdi.soap.main;


import com.ofdi.soap.main.business.BusinessCsv;
import com.ofdi.soap.main.business.EssJobs;
import com.ofdi.soap.main.utils.Helpers;
import com.ofdi.soap.main.utils.configs.YAMLConfigClient;
import com.ofdi.soap.main.utils.configs.YAMLConfigEssJobs;
import com.ofdi.soap.main.utils.configs.YAMLConfigFolder;
import com.ofdi.soap.services.dbService.OfdiExecutionControlService;
import com.ofdi.soap.services.integrationService.UploadFileToUcmRequestClient;
import com.ofdi.soap.services.mktImport.GetImportActivityStatusClient;
import com.ofdi.soap.services.mktImport.SubmitImportActivityRequestClient;
import com.ofdi.soap.services.integrationService.GetESSJobStatusRequestClient;
import com.ofdi.soap.services.integrationService.SubmitESSJobRequestClient;
import integrationService.wsdl.SubmitESSJobRequestResponse;
import mktImport.wsdl.SubmitImportActivityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AppStartupRunner implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);

    @Autowired
    private YAMLConfigFolder myConfigFolder;

    @Autowired
    private YAMLConfigClient myConfigClient;

    @Autowired
    private YAMLConfigEssJobs myConfigEssJobs;

    @Autowired
    private Helpers helpers;

//    @Autowired
//    GetESSJobStatusRequestClient gejsrc;
//
//    @Autowired
//    SubmitImportActivityRequestClient siarc;
//
//    @Autowired
//    GetImportActivityStatusClient giasc;
//
//    @Autowired
//    SubmitESSJobRequestClient sejrc;
//
//    @Autowired
//    UploadFileToUcmRequestClient ufturc;
//
//    @Autowired
//    OfdiExecutionControlService oecs;

    @Autowired
    EssJobs essJobs;

    @Autowired
    BusinessCsv businessCsv;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        SubmitESSJobRequestResponse submitESSJobRequestResponse = new SubmitESSJobRequestResponse();
        SubmitImportActivityResponse submitImportActivityResponse = new SubmitImportActivityResponse();

        List<String> params = new ArrayList<>();

        logger.info("Aplicacao iniciada...");
        logger.info("Verificando diretorio: " + myConfigFolder.getPath());

        if(helpers.verifyFolderCsv(myConfigFolder.getPath(), myConfigFolder.getCsvChefe() ,myConfigFolder.getCsvVendedor())) {

            logger.info("Importando arquivo chefe : " + myConfigFolder.getCsvChefe());
            //Import csv chefe
            businessCsv.importArchive(myConfigFolder.getCsvChefe(), myConfigFolder.getPath(), myConfigClient.getUser().getName());

            //Import csv vendedor
            businessCsv.importArchive(myConfigFolder.getCsvVendedor(), myConfigFolder.getPath(), myConfigClient.getUser().getName());

            //Send To LDAP
            essJobs.abstractService("Send to LDAP", myConfigEssJobs.getServices().getSendToLdap().getJobDefinitionName(), myConfigEssJobs.getServices().getSendToLdap().getPackageName(), myConfigEssJobs.getServices().getSendToLdap().getArguments());

            //Import Incentive Compensation Participants
            essJobs.abstractService("Import Incentive Compensation Participants", myConfigEssJobs.getServices().getIncentiveCompensationParticipantDetailImport().getJobDefinitionName(), myConfigEssJobs.getServices().getIncentiveCompensationParticipantDetailImport().getPackageName(), myConfigEssJobs.getServices().getIncentiveCompensationParticipantDetailImport().getArguments());

        }



//            String idChefe = helpers.toTimesTamp() + csvChefe.replace(".csv","");
//            oecs.insert(new OfdiExecutionControlModel(idChefe, csvChefe, path, username, "STARTED", 0, new Date()));
//            try{
//                submitImportActivityResponse = siarc.submitImportActivity(helpers.getFileContent(path + csvChefe));
//                logger.info("Sucesso ao importar arquivo chefe : " + csvChefe + " id: " + submitImportActivityResponse.getResult().getJobId().getValue());
//            }catch (Exception e){
//                logger.error("Erro ao importar " + csvChefe + " : " + e.getMessage());
//                oecs.insert(new OfdiExecutionControlModel(idChefe, csvChefe, path, username, "FAILED", 1, new Date()));
//            }
//            try{
//                giasc.getImportActivityStatus(submitImportActivityResponse.getResult().getJobId().getValue());
//                oecs.insert(new OfdiExecutionControlModel(idChefe, csvChefe, path, username, "IMPORTED", 2, new Date()));
//            }catch (Exception e){
//                logger.error("Erro ao importar " + csvChefe + " : " + e.getMessage());
//                oecs.insert(new OfdiExecutionControlModel(idChefe, csvChefe, path, username, "FAILED", 2, new Date()));
//            }
//
//            //Import vendedor
//
//            logger.info("Importando arquivo vendedor : " + csvChefe );
//
//            String idVendedor = helpers.toTimesTamp() + csvVendedor.replace(".csv","");
//            oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "STARTED", 0, new Date()));
//            try{
//                submitImportActivityResponse = siarc.submitImportActivity(helpers.getFileContent(path + csvVendedor));
//                logger.info("Sucesso ao importar arquivo chefe : " + csvVendedor + " id: " + submitImportActivityResponse.getResult().getJobId().getValue());
//            }catch (Exception e){
//                logger.error("Erro ao importar " + csvVendedor + " : " + e.getMessage());
//                oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "FAILED", 1, new Date()));
//            }
//            try{
//                giasc.getImportActivityStatus(submitImportActivityResponse.getResult().getJobId().getValue());
//                oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "IMPORTED", 2, new Date()));
//            }catch (Exception e){
//                logger.error("Erro ao importar " + csvVendedor + " : " + e.getMessage());
//                oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "FAILED", 2, new Date()));
//            }

            //Send to LDAP

//            params = new ArrayList<>();
//            params.add("ALL_USERS");
//
//            try{
//                logger.info("Mandando dados para LDAP...");
//                submitESSJobRequestResponse = sejrc.submitESSJobRequest("CopyPersonalData", "/oracle/apps/ess/hcm/users/", params);
//                //oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "IMPORTED", 2, new Date()));
//            }catch (Exception e){
//                logger.error("Erro ao mandar dados ao LDAP " + e.getMessage());
//                //oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "FAILED", 2, new Date()));
//            }
//
//            try{
//                gejsrc.getEssJobStatus(submitESSJobRequestResponse.getResult());
//                logger.info("Sucesso ao mandar dados para LDAP...");
//                //oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "IMPORTED", 2, new Date()));
//            }catch (Exception e){
//                logger.error("Erro ao mandar dados ao LDAP " + e.getMessage());
//                //oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "FAILED", 2, new Date()));
//            }
//
//            //Import Incentive Compensation Participants
//
//            params = new ArrayList<>();
//            params.add("300000001064232");
//            params.add("#NULL");
//            params.add("#NULL");
//            params.add("PARTICIPANT");
//            params.add("#NULL");
//            params.add("#NULL");
//            params.add("#NULL");
//            params.add("#NULL");
//            params.add("#NULL");
//            params.add("#NULL");
//            params.add("#NULL");
//            params.add("#NULL");
//            params.add("#NULL");
//            params.add("#NULL");
//            params.add("#NULL");
//            params.add("HR_EMPLOYEE");
//
//
//            try{
//                submitESSJobRequestResponse = sejrc.submitESSJobRequest("ParticipantsImportSQL", "/oracle/apps/ess/incentiveCompensation/cn/processes/transactionProcess/", params);
//                logger.info("Sucesso ao Import Incentive Compensation Participants...");
//                //oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "IMPORTED", 2, new Date()));
//            }catch (Exception e){
//                logger.error("Erro ao Import Incentive Compensation Participants " + e.getMessage());
//                //oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "FAILED", 2, new Date()));
//            }
//
//            try{
//                gejsrc.getEssJobStatus(submitESSJobRequestResponse.getResult());
//                logger.info("Sucesso ao Import Incentive Compensation Participants...");
//                //oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "IMPORTED", 2, new Date()));
//            }catch (Exception e){
//                logger.error("Erro ao Import Incentive Compensation Participants " + e.getMessage());
//                //oecs.insert(new OfdiExecutionControlModel(idVendedor, csvVendedor, path, username, "FAILED", 2, new Date()));
//            }
//
//        }
//
//        //zipChefe
//            String idZipChefe = helpers.toTimesTamp() + zipChefe.replace(".zip", "");
//
//    try {
//
//        //Import arquivos - UploadToUcm - Chefe
//        String resultImport = ufturc.uploadFileToUcmRequest(helpers.getFileContent(path + zipChefe), zipChefe, "zip", zipChefe, "marcelo.taboada@oracle.com", "FAFusionImportExport", "ic$/incentiveCompensationParticipant$/import$", zipChefe).getResult();
//        System.out.println(resultImport);
//
//        oecs.insert(new OfdiExecutionControlModel(idZipChefe, zipChefe, path, username, "IMPORTED", 1, new Date()));
//        //Incentive Compensation Participant Detail Import - Chefe
//        System.out.println("Incentive Compensation Participant Detail Import");
//        params = new ArrayList<>();
//
//        params.add(zipChefe);
//        params.add(resultImport);
//        params.add("N");
//
//        submitESSJobRequestResponse = sejrc.submitESSJobRequest("SrpInterfaceLoader", "/oracle/apps/ess/incentiveCompensation/cn/processes/transactionProcess/", params);
//        System.out.println(submitESSJobRequestResponse.getResult());
//        gejsrc.getEssJobStatus(submitESSJobRequestResponse.getResult());
//
//
//             //Assign Roles to Participants
//        params = new ArrayList<>();
//
//        params.add("300000001064232");
//        params.add(helpers.toDateNow());
//        params.add(helpers.toDateNow());
//        params.add("NEW");
//        params.add("ROLE_ASGN");
//        params.add("#NULL");
//
//        submitESSJobRequestResponse = sejrc.submitESSJobRequest("ParticipantAssignment", "/oracle/apps/ess/incentiveCompensation/cn/processes/setupProcess/", params);
//        System.out.println(submitESSJobRequestResponse.getResult());
//        gejsrc.getEssJobStatus(submitESSJobRequestResponse.getResult());
//        oecs.insert(new OfdiExecutionControlModel(idZipChefe, zipChefe, path, username, "PROCESSED", 2, new Date()));
//    }catch (Exception e){
//        oecs.insert(new OfdiExecutionControlModel(idZipChefe, zipChefe, path, username, "FAILED", 2, new Date()));
//        e.printStackTrace();
//    }
//
//
//
//
//
//        //zipVendedor
//        String idZipVendedor = helpers.toTimesTamp() + zipVendedor.replace(".zip", "");
//
//        try {
//
//            //Import arquivos - UploadToUcm - Vendedor
//            String resultImport = ufturc.uploadFileToUcmRequest(helpers.getFileContent(path + zipVendedor), zipVendedor, "zip", zipVendedor.replace(".zip", ""), "marcelo.taboada@oracle.com", "FAFusionImportExport", "ic/incentiveCompensationParticipantGoal/import", zipVendedor.replace(".zip", "")).getResult();
//            System.out.println(resultImport);
//
//            oecs.insert(new OfdiExecutionControlModel(idZipVendedor, zipVendedor, path, username, "IMPORTED", 1, new Date()));
//            //Incentive Compensation Participant Detail Import - Vendedor
//            System.out.println("Import Participants Goals");
//            params = new ArrayList<>();
//
//            params.add(zipVendedor);
//            params.add(resultImport);
//            params.add("N");
//
//            submitESSJobRequestResponse = sejrc.submitESSJobRequest("SrpGoalInterfaceLoader", "/oracle/apps/ess/incentiveCompensation/cn/processes/transactionProcess/", params);
//            System.out.println(submitESSJobRequestResponse.getResult());
//            gejsrc.getEssJobStatus(submitESSJobRequestResponse.getResult());
//
//            oecs.insert(new OfdiExecutionControlModel(idZipVendedor, zipVendedor, path, username, "PROCESSED", 2, new Date()));
//        }catch (Exception e){
//            oecs.insert(new OfdiExecutionControlModel(idZipVendedor, zipVendedor, path, username, "FAILED", 2, new Date()));
//            e.printStackTrace();
//        }
//
//
        System.exit(0);

}}
