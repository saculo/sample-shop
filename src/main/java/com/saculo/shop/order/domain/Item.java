package com.saculo.shop.order.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
class Item {
    UUID id;
    Price price;
    String name;
}
