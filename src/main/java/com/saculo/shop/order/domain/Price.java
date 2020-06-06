package com.saculo.shop.order.domain;

import io.vavr.control.Either;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
class Price {
    private BigDecimal value;

    static Either<PriceError, Price> of(BigDecimal value) {
        if(isCorrectPrice(value)) {
            return Either.left(PriceError.INCORRECT_PRICE);
        }
        return Either.right(new Price(value));
    }

    Price addPrice(BigDecimal value) {
        this.value = this.value.add(value);
        return this;
    }

    private Price (final BigDecimal value) {
        this.value = value;
    }

    private static boolean isCorrectPrice (final BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) <= 0;
    }

}
