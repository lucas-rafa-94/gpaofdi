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

import integrationService.wsdl.SubmitESSJobRequest;
import integrationService.wsdl.SubmitESSJobRequestResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;
import java.util.List;


public class SubmitESSJobRequestClient extends WebServiceGatewaySupport {


    public SubmitESSJobRequestResponse submitESSJobRequest(String jobDefinitionName, String jobPackageName, List<String> params, String defaultUri, String localPart, String xmlnsPrefix, String xmlnsValue) {

        SubmitESSJobRequest request = new SubmitESSJobRequest();

        request.setJobDefinitionName(jobDefinitionName);
        request.setJobPackageName(jobPackageName);

        for ( int i = 0; i < params.size(); i++){
            request.getParamList().add(params.get(i));
        }

        SubmitESSJobRequestResponse response = (SubmitESSJobRequestResponse) JAXBIntrospector.getValue(getWebServiceTemplate()
                .marshalSendAndReceive(
                        defaultUri,
                        new JAXBElement<>(new QName(xmlnsValue, localPart, xmlnsPrefix), SubmitESSJobRequest.class, request)));

        return response;
    }
}