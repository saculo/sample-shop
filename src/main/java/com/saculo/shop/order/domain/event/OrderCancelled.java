package com.saculo.shop.order.domain.event;

import com.saculo.shop.commons.DomainEvent;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
public class OrderCancelled implements DomainEvent {
    private final UUID eventId;
    private final UUID orderId;
    private final Instant time;
    private final Boolean cancelled;

    @Override
    public String type () {
        return "order-cancelled";
    }
}
