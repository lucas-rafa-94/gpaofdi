/* OCS - Oracle Consulting Services - Middleware
 *
 * Data de criação 03/12/2018
 * Autor: lucas.dos@oracle.com
 *
 * Projeto: GPA OFDI
 *
 *
 */
package com.ofdi.soap.services.mktImport;

import com.ofdi.soap.main.utils.configs.YAMLConfigMktImport;
import mktImport.wsdl.GetImportActivityStatus;
import mktImport.wsdl.GetImportActivityStatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;

public class GetImportActivityStatusClient extends WebServiceGatewaySupport {

    Logger logger = LoggerFactory.getLogger(GetImportActivityStatusClient.class);

    @Autowired
    private YAMLConfigMktImport myConfigMktImport;

    @Value("${utils.threadsleep}")
    private String threadSleep;

    public GetImportActivityStatusResponse getImportActivityStatus(long id) throws Exception{

        GetImportActivityStatusResponse response = new GetImportActivityStatusResponse();

        GetImportActivityStatus request = new GetImportActivityStatus();

        request.setImportJobId(id);

        do {
                Thread.sleep(Long.parseLong(threadSleep));
                response = (GetImportActivityStatusResponse) JAXBIntrospector.getValue(getWebServiceTemplate()
                        .marshalSendAndReceive(
                                myConfigMktImport.getDefaultUri(),
                                new JAXBElement<>(new QName(myConfigMktImport.getGetImportActivityStatus().getXmlnsFather().getName(), myConfigMktImport.getGetImportActivityStatus().getXmlnsFather().getLocalPart().get(0), myConfigMktImport.getGetImportActivityStatus().getXmlnsFather().getPrefix()), GetImportActivityStatus.class, request)));
                logger.info("Status import " + id + ": " +response.getResult().getStatus().getValue());
                if(response.getResult().getStatus().getValue().equals("COMPLETE_WITH_ERRORS")){
                    break;
                }
        } while (
            !response.getResult().getStatus().getValue().equals("COMPLETE")
        );

        return response;
    }

}
