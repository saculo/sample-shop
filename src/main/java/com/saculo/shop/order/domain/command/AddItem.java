package com.saculo.shop.order.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddItem {
    private UUID orderId;
    private UUID itemId;
    private Integer quantity;
    private BigDecimal price;
}
