package com.learn.ws.client.configuration;

import com.learn.ws.client.country.CountryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class CountryConfiguration {

    private static final String DEFAULT_URI = "http://localhost:8080/ws";
    private static final String CONTEXT_PATH = "com.learn.ws.client.wsdl";

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        //This package must match the package in <generatedPackage> specified in cdm pom.xml
        jaxb2Marshaller.setContextPath(CONTEXT_PATH);
        return jaxb2Marshaller;
    }
    /*@Bean
    public CountryClient countryClient(Jaxb2Marshaller jaxb2Marshaller){
        CountryClient client = new CountryClient();
        client.setDefaultUri(DEFAULT_URI);
        client.setMarshaller(jaxb2Marshaller);
        client.setUnmarshaller(jaxb2Marshaller);
        return client;
    }*/

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller jaxb2Marshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
        webServiceTemplate.setDefaultUri(
                DEFAULT_URI);

        return webServiceTemplate;
    }


}
