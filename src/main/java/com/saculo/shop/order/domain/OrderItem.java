package com.saculo.shop.order.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
class OrderItem {
    final UUID item;
    final Integer quantity;
    final Price price;

    @Override
    public boolean equals (final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderItem orderItem = (OrderItem) o;
        return Objects.equals(item, orderItem.item) &&
                Objects.equals(quantity, orderItem.quantity) &&
                Objects.equals(price, orderItem.price);
    }

    @Override
    public int hashCode () {
        return Objects.hash(item, quantity, price);
    }
}
