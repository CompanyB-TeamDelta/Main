package ua.edu.ukma.systemsdesign.dataprocessor.configs;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.EC2ContainerCredentialsProviderWrapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AwsConfig {
    private final AWSCredentialsProviderChain myCustomChain = new AWSCredentialsProviderChain(new EC2ContainerCredentialsProviderWrapper());

    @Bean
    public AmazonSQSAsync sqsAsync(){
        return AmazonSQSAsyncClientBuilder.standard().withCredentials(myCustomChain).withRegion(Regions.US_EAST_1.getName()).build();
    }
}
