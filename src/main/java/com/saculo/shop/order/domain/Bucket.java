package com.saculo.shop.order.domain;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.With;

import java.util.UUID;

@With
@Builder
class Bucket {
    final List<UUID> items;
}
