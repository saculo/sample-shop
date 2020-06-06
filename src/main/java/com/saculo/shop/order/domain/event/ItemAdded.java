package com.saculo.shop.order.domain.event;

import com.saculo.shop.commons.DomainEvent;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
public class ItemAdded implements DomainEvent {
    private final UUID orderId;
    private final UUID itemId;
    private final Integer quantity;
    private final BigDecimal price;

    @Override
    public String type () {
        return "item-added";
    }
}
