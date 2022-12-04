package edu.columbia.e6156.feed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public final class FeedService {
    private final DynamoDbClient ddbClient;

    private static final String FEED_TABLE = "PostFeed";

    @Autowired
    public FeedService(final DynamoDbClient ddbClient) {
        this.ddbClient = ddbClient;
    }

    public List<UUID> getPostsForUser(UUID userId) {
        QueryRequest request = QueryRequest.builder()
                .tableName(FEED_TABLE)
                .keyConditionExpression("userId = :uid")
                .expressionAttributeValues(Map.of(":uid", AttributeValue.fromS(userId.toString()))).build();

        QueryResponse response = ddbClient.query(request);
        List<Map<String, AttributeValue>> items = response.items();

        System.out.println(items);
        return items.stream().map(itemMap -> UUID.fromString(itemMap.get("postId").s())).collect(toList());
    }
}
