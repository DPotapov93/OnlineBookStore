package mate.academy.onlinebookstore01.service.impl;

import static org.mockito.Mockito.when;

import java.util.List;
import mate.academy.onlinebookstore01.dto.category.CategoryResponseDto;
import mate.academy.onlinebookstore01.mapper.CategoryMapper;
import mate.academy.onlinebookstore01.model.Category;
import mate.academy.onlinebookstore01.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    @DisplayName("Verify findAll() method works")
    public void findAll_ValidPageable_ReturnsAllCategories() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Novel");
        category.setDescription("Novel description");

        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setId(category.getId());
        responseDto.setName(category.getName());
        responseDto.setDescription(category.getDescription());

        Pageable pageable = PageRequest.of(0, 10);
        List<Category> categories = List.of(category);
        Page<Category> categoryPage = new PageImpl<>(categories, pageable, categories.size());

        List<CategoryResponseDto> expected = List.of(responseDto);

        when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);
        when(categoryMapper.toDto(category)).thenReturn(responseDto);

        List<CategoryResponseDto> actual = categoryService.getAll(pageable);

        Assertions.assertIterableEquals(expected, actual);
    }
}
