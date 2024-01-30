package mate.academy.onlinebookstore01.mapper;

import mate.academy.onlinebookstore01.config.MapperConfig;
import mate.academy.onlinebookstore01.dto.order.OrderDto;
import mate.academy.onlinebookstore01.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    OrderDto toDto(Order order);
}
