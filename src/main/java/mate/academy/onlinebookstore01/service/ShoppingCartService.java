package mate.academy.onlinebookstore01.service;

import mate.academy.onlinebookstore01.dto.cart.AddToCartRequestDto;
import mate.academy.onlinebookstore01.dto.cart.ShoppingCartDto;
import mate.academy.onlinebookstore01.dto.cart.UpdateCartItemRequestDto;

public interface ShoppingCartService {
    ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long userId);

    ShoppingCartDto getShoppingCartByUserId(Long userId);

    void deleteCartItemById(Long cartId);

    ShoppingCartDto updateByCartItemId(
            Long cartItemId,
            UpdateCartItemRequestDto requestDto
    );
}
