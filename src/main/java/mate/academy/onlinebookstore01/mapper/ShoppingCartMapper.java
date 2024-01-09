package mate.academy.onlinebookstore01.mapper;

import mate.academy.onlinebookstore01.config.MapperConfig;
import mate.academy.onlinebookstore01.dto.cart.ShoppingCartDto;
import mate.academy.onlinebookstore01.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    @Mapping(target = "userId", source = "user.id")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);

}
