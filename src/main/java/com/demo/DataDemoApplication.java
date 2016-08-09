package com.demo;

import com.demo.repository.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.demo")
@EnableJpaRepositories(basePackages = "com.demo.repository", repositoryBaseClass = CustomRepositoryImpl.class)
@EnableElasticsearchRepositories(basePackages = "com.demo.search.repository")
public class DataDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataDemoApplication.class, args);
    }
}
