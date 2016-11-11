package com.diyishuai.tomato;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.springframework.boot.SpringApplication.run;

/**
 * @author Bruce
 * @date 16/9/21
 */
@SpringBootApplication
public class WebApplication {
    private static final Logger log = Logger.getLogger(WebApplication.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = run(WebApplication.class, args);

        log.info("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            log.info(beanName);
        }
    }



}