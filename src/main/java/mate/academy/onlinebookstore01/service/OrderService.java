package mate.academy.onlinebookstore01.service;

import mate.academy.onlinebookstore01.dto.order.OrderDto;
import mate.academy.onlinebookstore01.dto.order.OrderItemDto;
import mate.academy.onlinebookstore01.dto.order.PlaceOrderRequestDto;
import mate.academy.onlinebookstore01.dto.order.UpdateOrderStatusRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    OrderDto placeOrder(Long userId, PlaceOrderRequestDto placeOrderRequestDto);

    List<OrderDto> getOrderHistory(Long userId, Pageable pageable);

    OrderDto updateOrderStatus(UpdateOrderStatusRequestDto updateOrderStatus);

    OrderItemDto getOrderItem(Long userId, Long itemId);
}
