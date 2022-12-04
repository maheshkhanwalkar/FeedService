package edu.columbia.e6156.feed.providers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Component
public final class AwsProvider {
    @Bean
    public DynamoDbClient getDDBClient() {
        return DynamoDbClient.create();
    }
}
