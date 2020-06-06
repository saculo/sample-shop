package com.saculo.shop.order.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceOrder {
    private UUID orderId;
    private UUID customerId;
}
