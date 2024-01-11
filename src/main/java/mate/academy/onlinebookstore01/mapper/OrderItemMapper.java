package mate.academy.onlinebookstore01.mapper;

import mate.academy.onlinebookstore01.config.MapperConfig;
import mate.academy.onlinebookstore01.dto.order.OrderItemDto;
import mate.academy.onlinebookstore01.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    OrderItemDto toDto(OrderItem orderItem);
}
