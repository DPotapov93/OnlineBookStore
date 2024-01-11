package mate.academy.onlinebookstore01.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore01.dto.order.OrderDto;
import mate.academy.onlinebookstore01.dto.order.OrderItemDto;
import mate.academy.onlinebookstore01.dto.order.PlaceOrderRequestDto;
import mate.academy.onlinebookstore01.dto.order.UpdateOrderStatusRequestDto;
import mate.academy.onlinebookstore01.exception.EntityNotFoundException;
import mate.academy.onlinebookstore01.mapper.OrderItemMapper;
import mate.academy.onlinebookstore01.mapper.OrderMapper;
import mate.academy.onlinebookstore01.model.*;
import mate.academy.onlinebookstore01.repository.OrderItemRepository;
import mate.academy.onlinebookstore01.repository.OrderRepository;
import mate.academy.onlinebookstore01.repository.ShoppingCartRepository;
import mate.academy.onlinebookstore01.repository.UserRepository;
import mate.academy.onlinebookstore01.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final String USER_NOT_FOUND_ID_EXCEPTION = "There is no user by id: ";
    private static final String SHOPPING_CART_NOT_FOUND_EXCEPTION
            = "There is no cart by user id: ";
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    @Transactional
    public OrderDto placeOrder(Long userId, PlaceOrderRequestDto placeOrderRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        USER_NOT_FOUND_ID_EXCEPTION + userId
                ));

        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        SHOPPING_CART_NOT_FOUND_EXCEPTION + userId
                ));
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            BigDecimal itemPrice = cartItem.getBook().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            total = total.add(itemPrice);
        }
        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.Status.PENDING);
        order.setShippingAddress(placeOrderRequestDto.getShippingAddress());
        order.setTotal(total);
        order = orderRepository.save(order);

        Set<OrderItem> orderItems = new HashSet<>(shoppingCart.getCartItems().size());
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            Book book = cartItem.getBook();

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setBook(book);
            orderItem.setOrder(order);
            orderItem.setPrice(book.getPrice());
            orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getOrderHistory(Long userId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto updateOrderStatus(UpdateOrderStatusRequestDto updateOrderStatus) {
        return null;
    }

    @Override
    public OrderItemDto getOrderItem(Long userId, Long itemId) {
        return null;
    }
}
