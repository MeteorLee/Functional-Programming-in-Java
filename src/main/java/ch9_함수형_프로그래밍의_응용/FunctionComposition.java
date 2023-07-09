package ch9_함수형_프로그래밍의_응용;

import ch9_함수형_프로그래밍의_응용.model.Order;
import ch9_함수형_프로그래밍의_응용.model.OrderLine;
import ch9_함수형_프로그래밍의_응용.priceprocessor.OrderLineAggregationPriceProcessor;
import ch9_함수형_프로그래밍의_응용.priceprocessor.TaxPriceProcessor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionComposition {
    public static void main(String[] args) {
        Function<Integer, Integer> multiplyByTwo = x -> x * 2;
        Function<Integer, Integer> addTen = x -> x + 10;

        Function<Integer, Integer> composedFunction = multiplyByTwo.andThen(addTen);
        System.out.println("composedFunction.apply(3) = " + composedFunction.apply(3));

        Order unprocessedOrder = new Order()
                .setId(1001L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))
                ));

        List<Function<Order, Order>> priceProcessor = getPriceProcessors(unprocessedOrder);

        Function<Order, Order> mergedPriceProcessors = priceProcessor.stream()
//                .reduce(Function.identity(), (priceProcessor1, priceProcessor2) -> priceProcessor1.andThen(priceProcessor2));
                .reduce(Function.identity(), Function::andThen);

        Order processedOrder = mergedPriceProcessors.apply(unprocessedOrder);
        System.out.println("processedOrder = " + processedOrder);
    }

    public static List<Function<Order, Order>> getPriceProcessors(Order order) {
        return Arrays.asList(new OrderLineAggregationPriceProcessor(),
                new TaxPriceProcessor(new BigDecimal("9.375")));
    }
}
