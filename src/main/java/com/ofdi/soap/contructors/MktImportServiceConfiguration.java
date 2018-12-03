/* OCS - Oracle Consulting Services - Middleware
 *
 * Data de criação 03/12/2018
 * Autor: lucas.dos@oracle.com
 *
 * Projeto: GPA OFDI
 *
 *
 */
package com.ofdi.soap.contructors;

import com.ofdi.soap.main.utils.configs.YAMLConfigClient;
import com.ofdi.soap.services.mktImport.GetImportActivityStatusClient;
import com.ofdi.soap.services.mktImport.SubmitImportActivityRequestClient;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;


@Configuration
public class MktImportServiceConfiguration {

    @Autowired
    YAMLConfigClient myConfigClient;

    @Bean
    public Jaxb2Marshaller marshallerMktImport() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("mktImport.wsdl");
        return marshaller;
    }

    @Bean
    public SubmitImportActivityRequestClient submitImportActivity(Jaxb2Marshaller marshallerMktImport) {
        SubmitImportActivityRequestClient client = new SubmitImportActivityRequestClient();
        client.setDefaultUri(myConfigClient.getDefaultUriMktImport());
        client.setMarshaller(marshallerMktImport);
        client.setUnmarshaller(marshallerMktImport);
        client.setMessageSender(httpComponentsMessageSender());
        return client;
    }

    @Bean
    public GetImportActivityStatusClient getImportActivityStatus(Jaxb2Marshaller marshallerMktImport) {
        GetImportActivityStatusClient client = new GetImportActivityStatusClient();
        client.setDefaultUri(myConfigClient.getDefaultUriMktImport());
        client.setMarshaller(marshallerMktImport);
        client.setUnmarshaller(marshallerMktImport);
        client.setMessageSender(httpComponentsMessageSender());
        return client;
    }


    @Bean
    public HttpComponentsMessageSender httpComponentsMessageSender() {
        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
        httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());
        return httpComponentsMessageSender;
    }

    @Bean
    public UsernamePasswordCredentials usernamePasswordCredentials() {
        return new UsernamePasswordCredentials(myConfigClient.getUser().getName(), myConfigClient.getUser().getPassword());
    }

}
