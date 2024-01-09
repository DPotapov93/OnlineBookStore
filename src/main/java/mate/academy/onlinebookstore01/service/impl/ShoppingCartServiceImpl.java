package mate.academy.onlinebookstore01.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore01.dto.cart.AddToCartRequestDto;
import mate.academy.onlinebookstore01.dto.cart.CartItemQuantityRequestDto;
import mate.academy.onlinebookstore01.dto.cart.ShoppingCartDto;
import mate.academy.onlinebookstore01.exception.EntityNotFoundException;
import mate.academy.onlinebookstore01.mapper.ShoppingCartMapper;
import mate.academy.onlinebookstore01.model.Book;
import mate.academy.onlinebookstore01.model.CartItem;
import mate.academy.onlinebookstore01.model.ShoppingCart;
import mate.academy.onlinebookstore01.model.User;
import mate.academy.onlinebookstore01.repository.BookRepository;
import mate.academy.onlinebookstore01.repository.CartItemRepository;
import mate.academy.onlinebookstore01.repository.ShoppingCartRepository;
import mate.academy.onlinebookstore01.repository.UserRepository;
import mate.academy.onlinebookstore01.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final String BOOK_ID_EXCEPTION = "There is no book by id: ";
    private static final String USER_ID_EXCEPTION = "There is no user by id: ";
    private static final String SHOPPING_CART_EXCEPTION = "There is no cart by user id: ";
    private static final String CART_EXCEPTION = "There is no cart by id: ";
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long userId) {
        Long bookId = requestDto.getBookId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(
                        () -> new EntityNotFoundException(BOOK_ID_EXCEPTION + bookId)
                );
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException(USER_ID_EXCEPTION + userId)
                );
        ShoppingCart shoppingCartFromDb = shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUser(user);
                    shoppingCartRepository.save(shoppingCart);
                    return shoppingCart;
                });

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(requestDto.getQuantity());
        cartItem.setBook(book);
        cartItem.setShoppingCart(shoppingCartFromDb);
        cartItemRepository.save(cartItem);
        shoppingCartFromDb.getCartItems().add(cartItem);
        return shoppingCartMapper.toDto(shoppingCartFromDb);
    }

    @Override
    public ShoppingCartDto getShoppingCartByUserId(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository
                .findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(SHOPPING_CART_EXCEPTION + userId));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteCartItemById(Long cartId) {
        cartItemRepository.deleteById(cartId);
    }

    @Override
    public ShoppingCartDto updateByCartItemId(
            Long cartItemId,
            CartItemQuantityRequestDto requestDto
    ) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(CART_EXCEPTION + cartItemId));
        cartItem.setQuantity(requestDto.getQuantity());
        ShoppingCart shoppingCart = cartItem.getShoppingCart();
        return shoppingCartMapper.toDto(shoppingCart);
    }
}
