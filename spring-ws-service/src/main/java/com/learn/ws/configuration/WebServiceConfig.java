package com.learn.ws.configuration;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    /**
     * Wsdl file will at http://localhost:8080/ws/countries.wsdl
     * @param countriesSchema : countriesSchema
     * @return DefaultWsdl11Definition : DefaultWsdl11Definition
     */
    @Bean(name = "countries")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://learn/ws/country-producing-web-service");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    /**
     * Wsdl file will at http://localhost:8080/ws/countries.wsdl
     * @param ticketsSchema : countriesSchema
     * @return DefaultWsdl11Definition : DefaultWsdl11Definition
     */
    @Bean(name = "tickets")
    public DefaultWsdl11Definition ticketsWsdl11Definition(XsdSchema ticketsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("TicketsPort");
        wsdl11Definition.setLocationUri("/ws");

        wsdl11Definition.setTargetNamespace("http://learn/ws/ticket-producing-web-service");
        wsdl11Definition.setSchema(ticketsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/countries.xsd"));
    }
    @Bean
    public XsdSchema ticketsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/ticket-service.xsd"));
    }
}