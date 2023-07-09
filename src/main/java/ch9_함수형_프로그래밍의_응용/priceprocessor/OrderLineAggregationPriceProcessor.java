package ch9_함수형_프로그래밍의_응용.priceprocessor;

import ch9_함수형_프로그래밍의_응용.model.Order;
import ch9_함수형_프로그래밍의_응용.model.OrderLine;

import java.math.BigDecimal;
import java.util.function.Function;

public class OrderLineAggregationPriceProcessor implements Function<Order, Order> {


    @Override
    public Order apply(Order order) {
        return order.setAmount(order.getOrderLines().stream()
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

    }
}
