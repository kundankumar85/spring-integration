package com.learn.ws.client.country;

import com.learn.ws.client.wsdl.GetCountryRequest;
import com.learn.ws.client.wsdl.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Component
public class CountryClient {//extends WebServiceGatewaySupport (Either you can use this or directly autowiere webservicetemplate check CountryConfiguration class.)
    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    private WebServiceTemplate webServiceTemplate;

    @Autowired
    public CountryClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public GetCountryResponse getCountry(String country){
        GetCountryRequest getCountryRequest = new GetCountryRequest();
        getCountryRequest.setName(country);

        log.info("Request to get country information for :{}",country);
        GetCountryResponse getCountryResponse = (GetCountryResponse)webServiceTemplate.marshalSendAndReceive("http://localhost:8080/ws/countries",getCountryRequest,new SoapActionCallback("http://learn/ws/country-producing-web-service"));
        return getCountryResponse;
    }
}
