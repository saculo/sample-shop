package com.saculo.shop.order.domain;

import com.saculo.shop.commons.DomainEvent;
import io.vavr.collection.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Repository
public class OrderHandler {
    private final MongoTemplate mongoTemplate;

    public OrderHandler (final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Transactional
    public void publish (final DomainEvent event) {
        mongoTemplate.insert(event, "events");
    }

    public Order recreate (final UUID orderId) {
        return List.ofAll(mongoTemplate.findAll(DomainEvent.class, "events"))
                   .foldLeft(new Order(orderId), Order::handle);
    }

    public void clear () {
        mongoTemplate.dropCollection("events");
    }
}
