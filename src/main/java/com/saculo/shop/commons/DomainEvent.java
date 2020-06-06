package com.saculo.shop.commons;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    String type();
}
