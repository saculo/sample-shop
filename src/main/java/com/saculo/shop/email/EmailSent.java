package com.saculo.shop.email;

import lombok.Builder;

import java.util.UUID;

@Builder
class EmailSent {
    final UUID sender;
    final UUID receiver;
    final String title;
    final String content;
}
