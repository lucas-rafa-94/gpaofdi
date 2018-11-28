package com.ofdi.soap.services.mktImport;

import mktImport.wsdl.GetImportActivityStatus;
import mktImport.wsdl.GetImportActivityStatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;

public class GetImportActivityStatusClient extends WebServiceGatewaySupport {

    Logger logger = LoggerFactory.getLogger(GetImportActivityStatusClient.class);

    public GetImportActivityStatusResponse getImportActivityStatus(long id) throws Exception{

        GetImportActivityStatusResponse response = new GetImportActivityStatusResponse();

        GetImportActivityStatus request = new GetImportActivityStatus();

        request.setImportJobId(id);

        do {
                Thread.sleep(36000l);
                response = (GetImportActivityStatusResponse) JAXBIntrospector.getValue(getWebServiceTemplate()
                        .marshalSendAndReceive(
                                "https://ehhs-test.fa.la1.oraclecloud.com:443/crmService/ImportPublicService",
                                new JAXBElement<>(new QName("http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/types/", "getImportActivityStatus", "typ"), GetImportActivityStatus.class, request)));
                logger.info("Status import " + id + ": " +response.getResult().getStatus().getValue());
        } while (
            !response.getResult().getStatus().getValue().equals("COMPLETE")
        );

        return response;
    }

}
