package com.gargjayesh.learning.blockchain.configs;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class H2WebConsoleConfiguration {
    @Bean
    public ServletRegistrationBean h2ServletBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
        bean.addUrlMappings("/console/**");
        return bean;
    }
}
