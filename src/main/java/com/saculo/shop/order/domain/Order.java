package com.saculo.shop.order.domain;

import com.saculo.shop.commons.DomainEvent;
import com.saculo.shop.order.domain.command.AddItem;
import com.saculo.shop.order.domain.command.CancelOrder;
import com.saculo.shop.order.domain.command.PlaceOrder;
import com.saculo.shop.order.domain.command.RemoveItem;
import com.saculo.shop.order.domain.event.ItemAdded;
import com.saculo.shop.order.domain.event.ItemRemoved;
import com.saculo.shop.order.domain.event.OrderCancelled;
import com.saculo.shop.order.domain.event.OrderPlaced;
import io.vavr.Predicates;
import io.vavr.collection.List;
import lombok.Getter;

import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

@Getter
class Order {
    private UUID id;
    private UUID customer;
    private List<OrderItem> items;
    private OrderState state;

    public Order (
            final UUID id, final UUID customer, final List<OrderItem> items,
            final OrderState state) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.state = state;
    }

    public Order (UUID id) {
        this.id = id;
    }

    Order addItem (final AddItem addItem) {
        this.items = this.items
                .append(new OrderItem(addItem.getItemId(), addItem.getQuantity(), Price.of(addItem.getPrice()).get()));
        return this;
    }

    Order removeItem (final RemoveItem removeItem) {
        this.items = this.items.remove(new OrderItem(removeItem.getItemId(), removeItem.getQuantity(),
                                        Price.of(removeItem.getPrice()).get()
        ));
        return this;
    }

    Order createOrder (final PlaceOrder placeOrder) {
        return new Order(placeOrder.getOrderId(), placeOrder.getOrderId(), List.empty(), OrderState.OPENED);
    }

    Order cancelOrder (final CancelOrder cancelOrder) {
        this.state = OrderState.CANCELLED;
        return this;
    }

    public Order handle (final DomainEvent event) {
        return Match(event).of(
                Case(
                        $(Predicates.instanceOf(OrderPlaced.class)),
                        command -> this.createOrder(new PlaceOrder(command.getOrderId(), command.getCustomerId()))
                ),
                Case(
                        $(Predicates.instanceOf(OrderCancelled.class)),
                        command -> this.cancelOrder(new CancelOrder(command.getOrderId()))
                ),
                Case(
                        $(Predicates.instanceOf(ItemAdded.class)),
                        command -> this.addItem(
                                new AddItem(command.getOrderId(), command.getItemId(), command.getQuantity(),
                                            command.getPrice()
                                ))
                ),
                Case(
                        $(Predicates.instanceOf(ItemRemoved.class)),
                        command -> this.removeItem(
                                new RemoveItem(command.getOrderId(), command.getItemId(), command.getQuantity(),
                                               command.getPrice()
                                ))
                )
        );
    }
}

