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

import com.ofdi.soap.main.utils.configs.YAMLConfigEssJobs;
import com.ofdi.soap.services.integrationService.GetESSJobStatusRequestClient;
import com.ofdi.soap.services.integrationService.SubmitESSJobRequestClient;
import integrationService.wsdl.SubmitESSJobRequestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public static  List<String> verifyInputs( List<String> params, List<String> inputs){
        if(inputs != null){
            List<String> newArray = new ArrayList<>();
            for(int i = 0; i < inputs.size(); i++){
                newArray.add(inputs.get(i).replace("'", ""));
            }
            return newArray;
        }else {
            List<String> newArray = new ArrayList<>();
            for(int i = 0; i < params.size(); i++){
                newArray.add(params.get(i).replace("'", ""));
            }
            return newArray;
        }
    }

    public void abstractService(String serviceName, String jobDefinition, String jobPackage, List<String> params, List<String> inputs){

        SubmitESSJobRequestResponse submitESSJobRequestResponse = new SubmitESSJobRequestResponse();

            try {
                logger.info("Servico " + serviceName);
                submitESSJobRequestResponse = sejrc.submitESSJobRequest(jobDefinition, jobPackage, verifyInputs(params,inputs), myConfigEssJobs.getDefaultUri(), myConfigEssJobs.getLocalPart().getSubmitESSJobRequest(), myConfigEssJobs.getXmlns().getPrefix(), myConfigEssJobs.getXmlns().getValue());
                logger.info("Submit " + serviceName + " id: " + submitESSJobRequestResponse.getResult());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Erro no servico " + serviceName + " : " + e.getMessage());
            }

            try {
                gejsrc.getEssJobStatus(submitESSJobRequestResponse.getResult(), myConfigEssJobs.getDefaultUri(), myConfigEssJobs.getLocalPart().getGetESSJobStatus(), myConfigEssJobs.getXmlns().getValue(), myConfigEssJobs.getXmlns().getPrefix());
                logger.info("Sucesso ao " + serviceName);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Erro ao " + serviceName + " : " + e.getMessage());
            }

    }
}
