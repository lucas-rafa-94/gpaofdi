package com.ofdi.soap.services.integrationService;

import integrationService.wsdl.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;
import java.util.List;

public class UploadFileToUcmRequestClient extends WebServiceGatewaySupport {

    @Value("${client.default-uri-integration-service}")
    private String defaultUri;

    @Value("${integration-service.xmlns.value}")
    private String xmlns;

    @Value("${integration-service.xmlns.prefix}")
    private String prefix;

    @Value("${integration-service.local-part.submitESSJob}")
    private String localPart;

    public UploadFileToUcmResponse uploadFileToUcmRequest(byte [] content, String fileName, String contentType, String documentTitle, String documentAuthor, String documentSecurityGroup, String documentAccount, String documentName) {

        UploadFileToUcm request = new UploadFileToUcm();
        DocumentDetails documentDetails = new DocumentDetails();

        documentDetails.setContent(content);
        documentDetails.setFileName(fileName);
      //  documentDetails.setContentType(new JAXBElement<>(new QName("http://xmlns.oracle.com/apps/financials/commonModules/shared/model/erpIntegrationService/", "ContentType", "erp"), String.class,contentType));
        documentDetails.setDocumentTitle(new JAXBElement<>(new QName("http://xmlns.oracle.com/apps/financials/commonModules/shared/model/erpIntegrationService/", "DocumentTitle", "erp"), String.class,documentTitle));
        documentDetails.setDocumentAuthor(new JAXBElement<>(new QName("http://xmlns.oracle.com/apps/financials/commonModules/shared/model/erpIntegrationService/", "DocumentAuthor", "erp"), String.class,documentAuthor));
        documentDetails.setDocumentSecurityGroup(new JAXBElement<>(new QName("http://xmlns.oracle.com/apps/financials/commonModules/shared/model/erpIntegrationService/", "DocumentSecurityGroup", "erp"), String.class,documentSecurityGroup));
        documentDetails.setDocumentAccount(new JAXBElement<>(new QName("http://xmlns.oracle.com/apps/financials/commonModules/shared/model/erpIntegrationService/", "DocumentAccount", "erp"), String.class, documentAccount));
        documentDetails.setDocumentName(new JAXBElement<>(new QName("http://xmlns.oracle.com/apps/financials/commonModules/shared/model/erpIntegrationService/", "DocumentName", "erp"), String.class, documentName));
       // documentDetails.setDocumentId(new JAXBElement<>(new QName("http://xmlns.oracle.com/apps/financials/commonModules/shared/model/erpIntegrationService/", "DocumentId", "erp"), String.class,"null"));

        request.setDocument(documentDetails);

        UploadFileToUcmResponse response = (UploadFileToUcmResponse) JAXBIntrospector.getValue(getWebServiceTemplate()
                .marshalSendAndReceive(
                        defaultUri,
                        new JAXBElement<>(new QName("http://xmlns.oracle.com/apps/financials/commonModules/shared/model/erpIntegrationService/types/", "uploadFileToUcm", "erp"), UploadFileToUcm.class, request)));

        return response;
    }
}
