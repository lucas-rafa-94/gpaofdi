package com.example.soap;

import correios.wsdl.ConsultaCEPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStarterRunner implements CommandLineRunner {

    @Autowired ConsultaCEPClient consultaCEPClient;

    @Override
    public void run(String...args) throws Exception {
        ConsultaCEPResponse response = consultaCEPClient.getQuote("08715150");
        System.out.println(response.getReturn().toString());
    }
}
