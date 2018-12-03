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

import com.ofdi.soap.main.utils.configs.YAMLConfigUploadToUcm;
import integrationService.wsdl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;

public class UploadFileToUcmRequestClient extends WebServiceGatewaySupport {

    @Autowired
    private YAMLConfigUploadToUcm myConfigUploadToUcm;

    public UploadFileToUcmResponse uploadFileToUcmRequest(byte [] content, String fileName, String contentType, String documentTitle, String documentAuthor, String documentSecurityGroup, String documentAccount, String documentName) {

        UploadFileToUcm request = new UploadFileToUcm();
        DocumentDetails documentDetails = new DocumentDetails();

        documentDetails.setContent(content);
        documentDetails.setFileName(fileName);
        documentDetails.setDocumentTitle(new JAXBElement<>(new QName(myConfigUploadToUcm.getUpload().getXmlnsChild().getName(), myConfigUploadToUcm.getUpload().getXmlnsChild().getLocalPart().get(0), myConfigUploadToUcm.getUpload().getXmlnsChild().getPrefix()), String.class,documentTitle));
        documentDetails.setDocumentAuthor(new JAXBElement<>(new QName(myConfigUploadToUcm.getUpload().getXmlnsChild().getName(), myConfigUploadToUcm.getUpload().getXmlnsChild().getLocalPart().get(1), myConfigUploadToUcm.getUpload().getXmlnsChild().getPrefix()), String.class,documentAuthor));
        documentDetails.setDocumentSecurityGroup(new JAXBElement<>(new QName(myConfigUploadToUcm.getUpload().getXmlnsChild().getName(), myConfigUploadToUcm.getUpload().getXmlnsChild().getLocalPart().get(2), myConfigUploadToUcm.getUpload().getXmlnsChild().getPrefix()), String.class,documentSecurityGroup));
        documentDetails.setDocumentAccount(new JAXBElement<>(new QName(myConfigUploadToUcm.getUpload().getXmlnsChild().getName(), myConfigUploadToUcm.getUpload().getXmlnsChild().getLocalPart().get(3), myConfigUploadToUcm.getUpload().getXmlnsChild().getPrefix()), String.class, documentAccount));
        documentDetails.setDocumentName(new JAXBElement<>(new QName(myConfigUploadToUcm.getUpload().getXmlnsChild().getName(), myConfigUploadToUcm.getUpload().getXmlnsChild().getLocalPart().get(4), myConfigUploadToUcm.getUpload().getXmlnsChild().getPrefix()), String.class, documentName));

        request.setDocument(documentDetails);

        UploadFileToUcmResponse response = (UploadFileToUcmResponse) JAXBIntrospector.getValue(getWebServiceTemplate()
                .marshalSendAndReceive(
                        myConfigUploadToUcm.getDefaultUri(),
                        new JAXBElement<>(new QName(myConfigUploadToUcm.getUpload().getXmlnsFather().getName(), myConfigUploadToUcm.getUpload().getXmlnsFather().getLocalPart().get(0), myConfigUploadToUcm.getUpload().getXmlnsFather().getPrefix()), UploadFileToUcm.class, request)));

        return response;
    }
}
