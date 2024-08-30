package com.withaeng.infrastructure.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
// TODO: Device SES Config from development environment (local or prod)
class SESConfig {

    @Value("\${cloud.aws.credentials.access-key}")
    private lateinit var accessKey: String

    @Value("\${cloud.aws.credentials.secret-key}")
    private lateinit var secretKey: String

    @Value("\${cloud.aws.region.static}")
    private lateinit var regionName: String

    @Bean
    fun amazonSimpleEmailService(): AmazonSimpleEmailService {
        val credentials = BasicAWSCredentials(accessKey, secretKey)
        return AmazonSimpleEmailServiceClientBuilder.standard()
            .withRegion(Regions.fromName(regionName))
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            .build()
    }
}