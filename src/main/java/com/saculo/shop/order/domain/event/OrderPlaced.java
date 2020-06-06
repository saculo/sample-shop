package com.saculo.shop.order.domain.event;

import com.saculo.shop.commons.DomainEvent;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class OrderPlaced implements DomainEvent {
    private final UUID eventId;
    private final UUID orderId;
    private final UUID customerId;
    private final Instant time;

    @Override
    public String type () {
        return "order-placed";
    }
}
