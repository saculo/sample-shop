package com.saculo.shop.order.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
class Order {
    final UUID customer;
    final Bucket bucket;
}
