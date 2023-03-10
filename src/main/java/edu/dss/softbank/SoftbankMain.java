
package edu.dss.softbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"edu.dss.softbank"})
@EnableJpaRepositories(basePackages="edu.dss.softbank.repositories")
@EnableTransactionManagement
@EntityScan(basePackages={"edu.dss.softbank.domain", "edu.dss.softbank.domain.vo"})
public class SoftbankMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SoftbankMain.class);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SoftbankMain.class, args);
    }

}
