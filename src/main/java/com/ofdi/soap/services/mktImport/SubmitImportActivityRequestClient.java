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
import mktImport.wsdl.ImportJobSubmitParams;
import mktImport.wsdl.SubmitImportActivity;
import mktImport.wsdl.SubmitImportActivityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;


public class SubmitImportActivityRequestClient extends WebServiceGatewaySupport {

    @Autowired
    private YAMLConfigMktImport myConfigMktImport;


    public SubmitImportActivityResponse submitImportActivity (byte [] content, boolean chefe) throws Exception{


        SubmitImportActivity request = new SubmitImportActivity();

        ImportJobSubmitParams importJobSubmitParams = new ImportJobSubmitParams();

        if(chefe){
            importJobSubmitParams.setHeaderRowIncluded(new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getName(), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getLocalPart().get(0), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getPrefix()), String.class,myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getValues().get(0)));
            importJobSubmitParams.setMappingNumber(new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getName(), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getLocalPart().get(1), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getPrefix()), String.class,myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getValues().get(1)));
            importJobSubmitParams.setDelimiter(new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getName(), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getLocalPart().get(2), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getPrefix()), String.class,myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getValues().get(2)));
            importJobSubmitParams.setFileFormat(new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getName(), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getLocalPart().get(3), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getPrefix()), String.class,myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getValues().get(3)));
            importJobSubmitParams.setFileContent(new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getName(), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getLocalPart().get(4), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsChild().getPrefix()), byte[].class,content));

            request.setImportJobSubmitParam(importJobSubmitParams);


            SubmitImportActivityResponse response = (SubmitImportActivityResponse) JAXBIntrospector.getValue(getWebServiceTemplate()
                    .marshalSendAndReceive(
                            myConfigMktImport.getDefaultUri(),
                            new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityChefe().getXmlnsFather().getName(), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsFather().getLocalPart().get(0), myConfigMktImport.getSubmitImportActivityChefe().getXmlnsFather().getPrefix()), SubmitImportActivity.class, request)));

            return response;

        }else {

            importJobSubmitParams.setHeaderRowIncluded(new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getName(), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getLocalPart().get(0), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getPrefix()), String.class, myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getValues().get(0)));
            importJobSubmitParams.setMappingNumber(new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getName(), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getLocalPart().get(1), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getPrefix()), String.class, myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getValues().get(1)));
            importJobSubmitParams.setDelimiter(new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getName(), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getLocalPart().get(2), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getPrefix()), String.class, myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getValues().get(2)));
            importJobSubmitParams.setFileFormat(new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getName(), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getLocalPart().get(3), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getPrefix()), String.class, myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getValues().get(3)));
            importJobSubmitParams.setFileContent(new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getName(), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getLocalPart().get(4), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsChild().getPrefix()), byte[].class, content));

            request.setImportJobSubmitParam(importJobSubmitParams);


            SubmitImportActivityResponse response = (SubmitImportActivityResponse) JAXBIntrospector.getValue(getWebServiceTemplate()
                    .marshalSendAndReceive(
                            myConfigMktImport.getDefaultUri(),
                            new JAXBElement<>(new QName(myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsFather().getName(), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsFather().getLocalPart().get(0), myConfigMktImport.getSubmitImportActivityVendedor().getXmlnsFather().getPrefix()), SubmitImportActivity.class, request)));

            return response;
        }
    }
}
