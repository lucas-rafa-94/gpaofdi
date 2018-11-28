package com.ofdi.soap.services.mktImport;


import mktImport.wsdl.ImportJobSubmitParams;
import mktImport.wsdl.SubmitImportActivity;
import mktImport.wsdl.SubmitImportActivityResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;
import java.io.File;
import java.nio.file.Files;



public class SubmitImportActivityRequestClient extends WebServiceGatewaySupport {

    @Value("${client.default-uri-mkt-import}")
    private String defaultUri;

    @Value("${mkt-import.xmlns.value}")
    private String xmlns;

    @Value("${mkt-import.xmlns.prefix}")
    private String prefix;

    @Value("${mkt-import.local-part.submitImportActivity}")
    private String localPart;

    public SubmitImportActivityResponse submitImportActivity (byte [] content) throws Exception{

        SubmitImportActivity request = new SubmitImportActivity();

        ImportJobSubmitParams importJobSubmitParams = new ImportJobSubmitParams();

        importJobSubmitParams.setHeaderRowIncluded(new JAXBElement<>(new QName("http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/", "HeaderRowIncluded", "mod"), String.class,"Y"));
        importJobSubmitParams.setMappingNumber(new JAXBElement<>(new QName("http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/", "MappingNumber", "mod"), String.class,"300000001723186"));
        importJobSubmitParams.setDelimiter(new JAXBElement<>(new QName("http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/", "Delimiter", "mod"), String.class,";"));
        importJobSubmitParams.setFileFormat(new JAXBElement<>(new QName("http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/", "FileFormat", "mod"), String.class,"OTHER_DELIMITER"));
        importJobSubmitParams.setFileContent(new JAXBElement<>(new QName("http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/", "FileContent", "mod"), byte[].class,content));

        request.setImportJobSubmitParam(importJobSubmitParams);


        SubmitImportActivityResponse response = (SubmitImportActivityResponse) JAXBIntrospector.getValue(getWebServiceTemplate()
                .marshalSendAndReceive(
                        "https://ehhs-test.fa.la1.oraclecloud.com:443/crmService/ImportPublicService",
                        new JAXBElement<>(new QName("http://xmlns.oracle.com/oracle/apps/marketing/commonMarketing/mktImport/model/types/", "submitImportActivity", "typ"), SubmitImportActivity.class, request)));

        return response;
    }
}
