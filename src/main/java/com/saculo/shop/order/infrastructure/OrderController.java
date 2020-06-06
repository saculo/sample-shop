package com.saculo.shop.order.infrastructure;

import com.saculo.shop.order.domain.event.OrderPlaced;
import com.saculo.shop.order.domain.command.AddItem;
import com.saculo.shop.order.domain.command.CancelOrder;
import com.saculo.shop.order.domain.event.ItemAdded;
import com.saculo.shop.order.domain.event.ItemRemoved;
import com.saculo.shop.order.domain.event.OrderCancelled;
import com.saculo.shop.order.domain.OrderHandler;
import com.saculo.shop.order.domain.command.PlaceOrder;
import com.saculo.shop.order.domain.command.RemoveItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
public class OrderController {
    private final OrderHandler orderHandler;

    public OrderController (final OrderHandler orderHandler) {
        this.orderHandler = orderHandler;
    }

    @PostMapping("/")
    public ResponseEntity<?> createOrder (@RequestBody final PlaceOrder placeOrder) {
        orderHandler.publish(OrderPlaced.builder()
                                        .eventId(UUID.randomUUID())
                                        .orderId(placeOrder.getOrderId())
                                        .time(Instant.now())
                                        .build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelOrder (@RequestBody final CancelOrder placeOrder) {
        orderHandler.publish(OrderCancelled.builder()
                                           .eventId(UUID.randomUUID())
                                           .orderId(placeOrder.getOrderId())
                                           .time(Instant.now())
                                           .cancelled(true)
                                           .build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/item/add")
    public ResponseEntity<?> addItem (@RequestBody final AddItem addItem) {
        orderHandler.publish(ItemAdded.builder()
                                      .itemId(addItem.getItemId())
                                      .orderId(addItem.getOrderId())
                                      .price(addItem.getPrice())
                                      .quantity(addItem.getQuantity())
                                      .build());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/item/remove")
    public ResponseEntity<?> removeItem (@RequestBody final RemoveItem removeItem) {
        orderHandler.publish(ItemRemoved.builder()
                                        .itemId(removeItem.getItemId())
                                        .orderId(removeItem.getOrderId())
                                        .price(removeItem.getPrice())
                                        .quantity(removeItem.getQuantity())
                                        .build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> takeOrders (@RequestBody final GetOrder uuid) {
        return ResponseEntity.ok(orderHandler.recreate(uuid.getUuid()));
    }

    @GetMapping("/take")
    public ResponseEntity<?> get () {
        return ResponseEntity.ok(orderHandler.get());
    }

    @PostMapping("/clear")
    public ResponseEntity<Void> clear () {
        orderHandler.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
