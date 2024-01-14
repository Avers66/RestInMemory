package avers66.restinmemory.mapper.v1;

import avers66.restinmemory.dto.ListOrderResponseDto;
import avers66.restinmemory.dto.OrderRequestDto;
import avers66.restinmemory.dto.OrderResponseDto;
import avers66.restinmemory.model.Order;
import avers66.restinmemory.service.ClientService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderMapper
 *
 * @Author Tretyakov Alexandr
 */

@Component
public class OrderMapper {

    public Order requestToOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setProduct(orderRequestDto.getProduct());
        order.setCost(orderRequestDto.getCost());
        order.setClientId(orderRequestDto.getClientId());
        return order;
    }

    public Order requestToOrder(Long orderId, OrderRequestDto orderRequestDto) {
        Order order = requestToOrder(orderRequestDto);
        order.setId(orderId);
        return order;
    }

    public OrderResponseDto orderToResponse(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setCost(order.getCost());
        orderResponseDto.setProduct(order.getProduct());
        orderResponseDto.setId(order.getId());
        return orderResponseDto;
    }

    public List<OrderResponseDto> listOrderToListOrderResponse(List<Order> orders) {
        return orders.stream().map(this::orderToResponse).collect(Collectors.toList());
    }

    public ListOrderResponseDto orderListToListOrderResponseDto(List<Order> orders) {
        ListOrderResponseDto listOrderResponseDto = new ListOrderResponseDto();
        listOrderResponseDto.setOrders(listOrderToListOrderResponse(orders));
        return listOrderResponseDto;
    }
}
