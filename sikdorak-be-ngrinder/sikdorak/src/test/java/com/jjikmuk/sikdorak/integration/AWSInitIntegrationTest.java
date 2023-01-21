package com.jjikmuk.sikdorak.integration;

import com.amazonaws.services.s3.AmazonS3;
import com.jjikmuk.sikdorak.common.properties.AwsProperties;
import com.jjikmuk.sikdorak.tool.mock.AWSMockConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AWSMockConfig.class)
public abstract class AWSInitIntegrationTest extends InitIntegrationTest {

    @Autowired
    protected AmazonS3 amazonS3;

    @Autowired
    protected AwsProperties awsProperties;
}
