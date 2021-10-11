package com.delta.document.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class StorageConfig {


     var accessKey: String = "AKIAV22YJWZIJPAD72HR"

     var accessSecret: String = "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"


    var region = "ap-south-1"

    @Bean
    open fun s3Client(): AmazonS3 {
        val credentials: AWSCredentials = BasicAWSCredentials(accessKey, accessSecret)
        return AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            .withRegion(region).build()
    }
}