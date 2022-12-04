package edu.columbia.e6156.feed.controllers;

import edu.columbia.e6156.feed.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public final class FeedController {
    private final FeedService service;

    @Autowired
    public FeedController(final FeedService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/v1/feed/{userId}")
    public List<UUID> getFeed(@PathVariable UUID userId) {
        return service.getPostsForUser(userId);
    }
}
