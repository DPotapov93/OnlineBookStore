package mate.academy.onlinebookstore01.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore01.dto.order.OrderDto;
import mate.academy.onlinebookstore01.dto.order.OrderItemDto;
import mate.academy.onlinebookstore01.dto.order.PlaceOrderRequestDto;
import mate.academy.onlinebookstore01.dto.order.UpdateOrderStatusRequestDto;
import mate.academy.onlinebookstore01.exception.EntityNotFoundException;
import mate.academy.onlinebookstore01.mapper.OrderItemMapper;
import mate.academy.onlinebookstore01.mapper.OrderMapper;
import mate.academy.onlinebookstore01.model.Book;
import mate.academy.onlinebookstore01.model.CartItem;
import mate.academy.onlinebookstore01.model.Order;
import mate.academy.onlinebookstore01.model.OrderItem;
import mate.academy.onlinebookstore01.model.ShoppingCart;
import mate.academy.onlinebookstore01.model.User;
import mate.academy.onlinebookstore01.repository.OrderItemRepository;
import mate.academy.onlinebookstore01.repository.OrderRepository;
import mate.academy.onlinebookstore01.repository.ShoppingCartRepository;
import mate.academy.onlinebookstore01.repository.UserRepository;
import mate.academy.onlinebookstore01.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final String USER_ID_NOT_FOUND_EXCEPTION = "There is no user by id: ";
    private static final String ORDER_ID_NOT_FOUND_EXCEPTION = "There is no order by id: ";
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
                        USER_ID_NOT_FOUND_EXCEPTION + userId
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getOrderHistory(Long userId, Pageable pageable) {
        List<Order> orders = orderRepository.findAllByUserId(userId, pageable);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderDto updateOrderStatus(Long id, UpdateOrderStatusRequestDto updateOrderStatus) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ORDER_ID_NOT_FOUND_EXCEPTION + id)
        );
        order.setStatus(Order.Status.valueOf(updateOrderStatus.getStatus()));
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderItemDto getOrderItem(Long itemId, Long orderId) {
        OrderItem orderItem = orderItemRepository.findByOrderIdAndId(orderId, itemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "OrderItem with id: " + itemId
                + " - not found in order with id: " + orderId));
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public List<OrderItemDto> getAllOrderItemsByOrderId(Long orderId, Pageable pageable) {
        return orderItemRepository.findAllByOrderId(orderId, pageable).stream()
                .map(orderItemMapper::toDto)
                .toList();
    }
}
