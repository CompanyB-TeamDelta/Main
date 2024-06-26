package ua.edu.ukma.systemsdesign.dataprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataProcessorApplication.class, args);
    }

}
