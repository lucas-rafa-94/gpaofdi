package com.example.soap.contructors;

import com.example.soap.services.ConsultaCEPClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ConsultaCEPConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("correios.wsdl");
        return marshaller;
    }

    @Bean
    public ConsultaCEPClient getCep(Jaxb2Marshaller marshaller) {
        ConsultaCEPClient client = new ConsultaCEPClient();
        client.setDefaultUri("https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}