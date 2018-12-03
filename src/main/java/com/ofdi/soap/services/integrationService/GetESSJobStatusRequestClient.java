/* OCS - Oracle Consulting Services - Middleware
 *
 * Data de criação 03/12/2018
 * Autor: lucas.dos@oracle.com
 *
 * Projeto: GPA OFDI
 *
 *
 */
package com.ofdi.soap.services.integrationService;

import integrationService.wsdl.GetESSJobStatus;
import integrationService.wsdl.GetESSJobStatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;


public class GetESSJobStatusRequestClient extends WebServiceGatewaySupport {

    Logger logger = LoggerFactory.getLogger(GetESSJobStatusRequestClient.class);

    @Value("${utils.threadsleep}")
    private String threadSleep;

    public GetESSJobStatusResponse getEssJobStatus(Long requestId , String defaultUri, String localPart, String xmlnsValue, String xmlnsPrefix) throws Exception {

        GetESSJobStatus request = new GetESSJobStatus();
        GetESSJobStatusResponse response = new GetESSJobStatusResponse();
        request.setRequestId(requestId);

        do {
                Thread.sleep(Long.parseLong(threadSleep));
                response = (GetESSJobStatusResponse) JAXBIntrospector.getValue(getWebServiceTemplate()
                        .marshalSendAndReceive(
                                defaultUri,
                                new JAXBElement<>(new QName(xmlnsValue, localPart, xmlnsPrefix), GetESSJobStatus.class, request)));
            logger.info("id: " + requestId + " status: " + response.getResult());
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
