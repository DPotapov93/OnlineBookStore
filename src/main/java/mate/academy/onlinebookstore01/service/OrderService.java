package mate.academy.onlinebookstore01.service;

import java.util.List;
import mate.academy.onlinebookstore01.dto.order.OrderDto;
import mate.academy.onlinebookstore01.dto.order.OrderItemDto;
import mate.academy.onlinebookstore01.dto.order.PlaceOrderRequestDto;
import mate.academy.onlinebookstore01.dto.order.UpdateOrderStatusRequestDto;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDto placeOrder(Long userId, PlaceOrderRequestDto placeOrderRequestDto);

    List<OrderDto> getOrderHistory(Long userId, Pageable pageable);

    OrderDto updateOrderStatus(Long id, UpdateOrderStatusRequestDto updateOrderStatus);

    OrderItemDto getOrderItem(Long itemId, Long orderId);

    List<OrderItemDto> getAllOrderItemsByOrderId(Long orderId, Pageable pageable);
}
