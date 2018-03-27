package com.example.soap.services;



import correios.wsdl.ConsultaCEP;
import correios.wsdl.ConsultaCEPResponse;


import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;

public class ConsultaCEPClient extends WebServiceGatewaySupport {

    public ConsultaCEPResponse getCep(String cep) {
        ConsultaCEP request = new ConsultaCEP();
        request.setCep(cep);
        ConsultaCEPResponse response = (ConsultaCEPResponse) JAXBIntrospector.getValue(getWebServiceTemplate()
                .marshalSendAndReceive(
                        "https://apps.correios.com.br/gragra/AtendeClienteService/AtendeCliente",
                        new JAXBElement<ConsultaCEP>(new QName("http://cliente.bean.master.sigep.bsb.correios.com.br/", "consultaCEP", "cli"), ConsultaCEP.class, request)));

        return response;
    }

}