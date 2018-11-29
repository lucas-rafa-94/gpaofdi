package com.ofdi.soap.main;

import com.ofdi.soap.main.business.BusinessCsv;
import com.ofdi.soap.main.business.EssJobs;
import com.ofdi.soap.main.business.UploadToUcm;
import com.ofdi.soap.main.utils.Helpers;
import com.ofdi.soap.main.utils.configs.YAMLConfigClient;
import com.ofdi.soap.main.utils.configs.YAMLConfigEssJobs;
import com.ofdi.soap.main.utils.configs.YAMLConfigFolder;
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

    @Value("${utils.accountparticipant}")
    private String accountParticipant;

    @Value("${utils.accountgoal}")
    private String accountGoal;

    @Value("${utils.file}")
    private String typeFile;

    @Value("${utils.role}")
    private String role;

    @Value("${utils.switch}")
    private String s;

    @Autowired
    private YAMLConfigFolder myConfigFolder;

    @Autowired
    private YAMLConfigClient myConfigClient;

    @Autowired
    private YAMLConfigEssJobs myConfigEssJobs;

    @Autowired
    private Helpers helpers;

    @Autowired
    UploadToUcm uploadToUcm;

    @Autowired
    EssJobs essJobs;

    @Autowired
    BusinessCsv businessCsv;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<String> params = new ArrayList<>();
        String result= "";

        logger.info("Aplicacao iniciada...");
        logger.info("Verificando diretorio: " + myConfigFolder.getPath());

        if(helpers.verifyFolderCsv(myConfigFolder.getPath(), myConfigFolder.getCsvChefe() ,myConfigFolder.getCsvVendedor())) {

            logger.info("Importando arquivo chefe : " + myConfigFolder.getCsvChefe());
            //Import csv chefe
            businessCsv.importArchive(myConfigFolder.getCsvChefe(), myConfigFolder.getPath(), myConfigClient.getUser().getName(), true);

            //Import csv vendedor
            businessCsv.importArchive(myConfigFolder.getCsvVendedor(), myConfigFolder.getPath(), myConfigClient.getUser().getName(), false);

            //Send To LDAP
            essJobs.abstractService("Send to LDAP", myConfigEssJobs.getServices().getSendToLdap().getJobDefinitionName(), myConfigEssJobs.getServices().getSendToLdap().getPackageName(), myConfigEssJobs.getServices().getSendToLdap().getArguments(), null);

            //Import Incentive Compensation Participants
            essJobs.abstractService("Import Incentive Compensation Participants", myConfigEssJobs.getServices().getImportIncentiveCompensationParticipants().getJobDefinitionName(), myConfigEssJobs.getServices().getImportIncentiveCompensationParticipants().getPackageName(), myConfigEssJobs.getServices().getImportIncentiveCompensationParticipants().getArguments(), null);

        }

        //Participant Detail Chefe

        result = uploadToUcm.uploadFile(myConfigFolder.getPath(),helpers.getFileContent(myConfigFolder.getPath() + myConfigFolder.getZipChefe()), myConfigFolder.getZipChefe(), typeFile, myConfigFolder.getZipChefe(), myConfigClient.getUser().getName(), role, accountParticipant, myConfigFolder.getZipChefe());
        essJobs.abstractService("Incentive Compensation Participant Detail Import - Chefe ", myConfigEssJobs.getServices().getIncentiveCompensationParticipantDetailImport().getJobDefinitionName(), myConfigEssJobs.getServices().getIncentiveCompensationParticipantDetailImport().getPackageName(), myConfigEssJobs.getServices().getIncentiveCompensationParticipantDetailImport().getArguments(), helpers.getImportDataParams(myConfigFolder.getZipChefe(), result, s));
        essJobs.abstractService("Assign Roles to Participants - Chefe ", myConfigEssJobs.getServices().getAssignRolesToParticipants().getJobDefinitionName(), myConfigEssJobs.getServices().getAssignRolesToParticipants().getPackageName(), myConfigEssJobs.getServices().getAssignRolesToParticipants().getArguments(), helpers.getIncentiveParams());

        //Participant Detail Vendedor

        result = uploadToUcm.uploadFile(myConfigFolder.getPath(),helpers.getFileContent(myConfigFolder.getPath() + myConfigFolder.getZipVendedor()), myConfigFolder.getZipVendedor(), typeFile, myConfigFolder.getZipVendedor(), myConfigClient.getUser().getName(), role, accountParticipant, myConfigFolder.getZipVendedor());
        essJobs.abstractService("Incentive Compensation Participant Detail Import - Vendedor ", myConfigEssJobs.getServices().getIncentiveCompensationParticipantDetailImport().getJobDefinitionName(), myConfigEssJobs.getServices().getIncentiveCompensationParticipantDetailImport().getPackageName(), myConfigEssJobs.getServices().getIncentiveCompensationParticipantDetailImport().getArguments(), helpers.getImportDataParams(myConfigFolder.getZipVendedor(), result, s));
        essJobs.abstractService("Assign Roles to Participants - Vendedor ", myConfigEssJobs.getServices().getAssignRolesToParticipants().getJobDefinitionName(), myConfigEssJobs.getServices().getAssignRolesToParticipants().getPackageName(), myConfigEssJobs.getServices().getAssignRolesToParticipants().getArguments(), helpers.getIncentiveParams());

       //Goal Chefe

        result = uploadToUcm.uploadFile(myConfigFolder.getPath(),helpers.getFileContent(myConfigFolder.getPath() + myConfigFolder.getZipGoalChefe()), myConfigFolder.getZipGoalChefe(), typeFile, myConfigFolder.getZipGoalChefe(), myConfigClient.getUser().getName(), role, accountGoal, myConfigFolder.getZipGoalChefe());
        essJobs.abstractService("Import Participants Goals - Chefe ", myConfigEssJobs.getServices().getImportParticipantsGoals().getJobDefinitionName(), myConfigEssJobs.getServices().getImportParticipantsGoals().getPackageName(), myConfigEssJobs.getServices().getImportParticipantsGoals().getArguments(), helpers.getImportDataParams(myConfigFolder.getZipGoalChefe(), result, s));

        //Goal Vendedor

        result = uploadToUcm.uploadFile(myConfigFolder.getPath(),helpers.getFileContent(myConfigFolder.getPath() + myConfigFolder.getZipGoalVendedor()), myConfigFolder.getZipGoalVendedor(), typeFile, myConfigFolder.getZipGoalVendedor(), myConfigClient.getUser().getName(), role, accountGoal, myConfigFolder.getZipGoalVendedor());
        essJobs.abstractService("Import Participants Goals - Vendedor ", myConfigEssJobs.getServices().getImportParticipantsGoals().getJobDefinitionName(), myConfigEssJobs.getServices().getImportParticipantsGoals().getPackageName(), myConfigEssJobs.getServices().getImportParticipantsGoals().getArguments(), helpers.getImportDataParams(myConfigFolder.getZipGoalVendedor(), result, s));

        logger.info("Encerrando Aplicacao...");
        System.exit(0);

}}
