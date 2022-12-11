package ru.nshi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SongsApplication {
    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(SongsApplication.class, args);




//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName + " = " + context.getBean(beanDefinitionName)
//                .getClass()
//                .getSimpleName());
//        }
    }
}