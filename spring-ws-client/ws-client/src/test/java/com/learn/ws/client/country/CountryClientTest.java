package com.learn.ws.client.country;

import com.learn.ws.client.configuration.CountryConfiguration;
import com.learn.ws.client.wsdl.GetCountryResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//@ContextConfiguration(classes = CountryConfiguration.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CountryClientTest {
    @Autowired
    private CountryClient countryClient;

    @Test
    public void getCountry() {
        GetCountryResponse getCountryResponse = countryClient.getCountry("Poland");
        Assert.assertNotNull(getCountryResponse);
        Assert.assertNotNull(getCountryResponse.getCountry().getCurrency());
    }
}