package com.sky.config;


import com.sky.properties.AliOSSProperties;
import com.sky.utils.AliOSSUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AliOSSConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AliOSSUtil aliOSSUtil(AliOSSProperties aliOSSProperties) {
        return new AliOSSUtil(aliOSSProperties.getEndpoint(), aliOSSProperties.getBucketName(), aliOSSProperties.getDirectory());
    }
}
