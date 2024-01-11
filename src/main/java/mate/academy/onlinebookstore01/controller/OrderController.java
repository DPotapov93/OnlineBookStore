package mate.academy.onlinebookstore01.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore01.dto.order.OrderDto;
import mate.academy.onlinebookstore01.dto.order.OrderItemDto;
import mate.academy.onlinebookstore01.dto.order.PlaceOrderRequestDto;
import mate.academy.onlinebookstore01.dto.order.UpdateOrderStatusRequestDto;
import mate.academy.onlinebookstore01.model.User;
import mate.academy.onlinebookstore01.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/orders")
@Tag(name = "Order management", description = "Endpoints for managing orders")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Operation(summary = "Place a new order")
    public OrderDto placeOrder(
            @RequestBody PlaceOrderRequestDto placeOrderRequestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.placeOrder(user.getId(), placeOrderRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get orders history")
    public List<OrderDto> getOrderHistory(
            Authentication authentication,
            Pageable pageable
    ) {
        User user = (User) authentication.getPrincipal();
        return orderService.getOrderHistory(user.getId(), pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    @Operation(summary = "Update an order status")
    public OrderDto updateOrderStatus(@RequestBody @Valid UpdateOrderStatusRequestDto requestDto,
                                      @PathVariable(name = "id") Long orderId) {
        return orderService.updateOrderStatus(orderId, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{order-id}/items")
    @Operation(summary = "Get order items")
    public List<OrderItemDto> getOrderItemsDto(
            @PathVariable(name = "order-id") Long orderId,
            Pageable pageable
    ) {
        return orderService.getAllOrderItemsByOrderId(orderId, pageable);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{order-id}/items/{item-id}")
    @Operation(summary = "Get order item details")
    public OrderItemDto getOrderItem(@PathVariable(name = "order-id") Long orderId,
                                     @PathVariable(name = "item-id") Long itemId
    ) {
        return orderService.getOrderItem(orderId, itemId);
    }
}
