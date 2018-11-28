package com.ofdi.soap.services.integrationService;

import integrationService.wsdl.GetESSJobStatus;
import integrationService.wsdl.GetESSJobStatusResponse;
import mktImport.wsdl.GetImportActivityStatus;
import mktImport.wsdl.GetImportActivityStatusResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;


public class GetESSJobStatusRequestClient extends WebServiceGatewaySupport {

    public GetESSJobStatusResponse getEssJobStatus(Long requestId , String defaultUri, String localPart, String xmlnsValue, String xmlnsPrefix) throws Exception {

        GetESSJobStatus request = new GetESSJobStatus();
        GetESSJobStatusResponse response = new GetESSJobStatusResponse();
        request.setRequestId(requestId);

        do {
                Thread.sleep(36000l);
                response = (GetESSJobStatusResponse) JAXBIntrospector.getValue(getWebServiceTemplate()
                        .marshalSendAndReceive(
                                defaultUri,
                                new JAXBElement<>(new QName(xmlnsValue, localPart, xmlnsPrefix), GetESSJobStatus.class, request)));
                System.out.println(response.getResult());
        } while(
                        !response.getResult().equalsIgnoreCase("SUCCEEDED") &&
                        !response.getResult().equalsIgnoreCase("SUCCESS_STATUS") &&
                        !response.getResult().equalsIgnoreCase("ERROR") &&
                        !response.getResult().equalsIgnoreCase("Error Manual Recovery") &&
                        !response.getResult().equalsIgnoreCase("Expired") &&
                        !response.getResult().equalsIgnoreCase("Hold") &&
                        !response.getResult().equalsIgnoreCase("Schedule Ended") &&
                        !response.getResult().equalsIgnoreCase("Validation Failed") &&
                        !response.getResult().equalsIgnoreCase("Warning") &&
                        !response.getResult().equalsIgnoreCase("Warning") &&
                        !response.getResult().equalsIgnoreCase("Canceled") &&
                        !response.getResult().equalsIgnoreCase("Canceling")
        );
        return response;
    }
}
