package mate.academy.onlinebookstore01.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore01.dto.order.OrderDto;
import mate.academy.onlinebookstore01.dto.order.PlaceOrderRequestDto;;
import mate.academy.onlinebookstore01.model.User;
import mate.academy.onlinebookstore01.service.OrderService;
import org.springframework.security.core.Authentication;
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

    @PostMapping
    @Operation(summary = "Place a new order")
    public OrderDto placeOrder (
            @RequestBody PlaceOrderRequestDto placeOrderRequestDto,
            Authentication authentication
            ) {
        User user = (User) authentication.getPrincipal();
        return orderService.placeOrder(user.getId(), placeOrderRequestDto);
    }
}
