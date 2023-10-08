package com.thp.customerbehavior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CustomerBehaviorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerBehaviorApplication.class, args);
    }
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { return application.sources(CustomerBehaviorApplication.class); }
}
