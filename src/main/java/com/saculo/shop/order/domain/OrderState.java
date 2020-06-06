package com.saculo.shop.order.domain;

import lombok.Getter;

@Getter
enum OrderState {
    OPENED, PAID, DELIVERED, CANCELLED
}
