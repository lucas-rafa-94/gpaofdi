package com.example.soap.controller;

import com.example.soap.services.ConsultaCEPClient;
import correios.wsdl.EnderecoERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CorreiosController {

    @Autowired ConsultaCEPClient consultaCEPClient;

    @RequestMapping(value = "busca-cep/{cep}", method = RequestMethod.GET)
    public EnderecoERP buscarPorPlaca(@PathVariable(value="cep") String cep){
        return consultaCEPClient.getCep(cep).getReturn();
    }

}
