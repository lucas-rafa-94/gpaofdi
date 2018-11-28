package com.ofdi.soap.main.business;

import com.ofdi.soap.main.utils.configs.YAMLConfigEssJobs;
import com.ofdi.soap.services.integrationService.GetESSJobStatusRequestClient;
import com.ofdi.soap.services.integrationService.SubmitESSJobRequestClient;
import integrationService.wsdl.SubmitESSJobRequestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EssJobs {

    Logger logger = LoggerFactory.getLogger(EssJobs.class);

    @Autowired
    private YAMLConfigEssJobs myConfigEssJobs;

    @Autowired
    SubmitESSJobRequestClient sejrc;

    @Autowired
    GetESSJobStatusRequestClient gejsrc;

    public void abstractService(String serviceName, String jobDefinition, String jobPackage, List<String> params){

        SubmitESSJobRequestResponse submitESSJobRequestResponse = new SubmitESSJobRequestResponse();

        try{
                logger.info("Servico " + serviceName);
                submitESSJobRequestResponse = sejrc.submitESSJobRequest(jobDefinition, jobPackage, params, myConfigEssJobs.getDefaultUri(), myConfigEssJobs.getLocalPart().getSubmitESSJobRequest(), myConfigEssJobs.getXmlns().getPrefix(), myConfigEssJobs.getXmlns().getValue());
            }catch (Exception e){
                logger.error("Erro no servico " + serviceName + " : " + e.getMessage());
            }

            try{
                gejsrc.getEssJobStatus(submitESSJobRequestResponse.getResult(), myConfigEssJobs.getDefaultUri(), myConfigEssJobs.getLocalPart().getGetESSJobStatus(), myConfigEssJobs.getXmlns().getPrefix(), myConfigEssJobs.getXmlns().getValue());
                logger.info("Sucesso ao " + serviceName);
            }catch (Exception e){
                logger.error("Erro ao " + serviceName + " : " + e.getMessage());
            }
    }
}
