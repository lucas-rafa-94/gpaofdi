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
import com.ofdi.soap.services.integrationService.GetESSJobStatusRequestClient;
import com.ofdi.soap.services.integrationService.UploadFileToUcmRequestClient;
import org.apache.http.auth.UsernamePasswordCredentials;
import com.ofdi.soap.services.integrationService.SubmitESSJobRequestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;


@Configuration
public class IntegrationServiceConfiguration {

    @Autowired
    YAMLConfigClient myConfigClient;

    @Bean
    public Jaxb2Marshaller marshallerItegrationService() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("integrationService.wsdl");
        return marshaller;
    }

    @Bean
    public SubmitESSJobRequestClient submitESSJobRequest(Jaxb2Marshaller marshallerItegrationService) {
        SubmitESSJobRequestClient client = new SubmitESSJobRequestClient();
        client.setDefaultUri(myConfigClient.getDefaultUriIntegrationService());
        client.setMarshaller(marshallerItegrationService);
        client.setUnmarshaller(marshallerItegrationService);
        client.setMessageSender(httpComponentsMessageSender());
        return client;
    }

    @Bean
    public UploadFileToUcmRequestClient uploadFileToUcmRequest(Jaxb2Marshaller marshallerItegrationService) {
        UploadFileToUcmRequestClient client = new UploadFileToUcmRequestClient();
        client.setDefaultUri(myConfigClient.getDefaultUriIntegrationService());
        client.setMarshaller(marshallerItegrationService);
        client.setUnmarshaller(marshallerItegrationService);
        client.setMessageSender(httpComponentsMessageSender());
        return client;
    }


    @Bean
    public GetESSJobStatusRequestClient getEssJobStatus(Jaxb2Marshaller marshallerItegrationService) {
        GetESSJobStatusRequestClient client = new GetESSJobStatusRequestClient();
        client.setDefaultUri(myConfigClient.getDefaultUriIntegrationService());
        client.setMarshaller(marshallerItegrationService);
        client.setUnmarshaller(marshallerItegrationService);
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