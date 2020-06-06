package com.saculo.shop.order.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PriceError {
    INCORRECT_PRICE("Price got incorrect value");

    private final String message;
}
