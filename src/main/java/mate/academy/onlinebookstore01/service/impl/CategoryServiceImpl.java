package mate.academy.onlinebookstore01.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore01.dto.category.CategoryRequestDto;
import mate.academy.onlinebookstore01.dto.category.CategoryResponseDto;
import mate.academy.onlinebookstore01.exception.EntityNotFoundException;
import mate.academy.onlinebookstore01.mapper.CategoryMapper;
import mate.academy.onlinebookstore01.model.Category;
import mate.academy.onlinebookstore01.repository.CategoryRepository;
import mate.academy.onlinebookstore01.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private static final String FIND_BY_ID_EXCEPTION
            = "Can`t find category: ";
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(FIND_BY_ID_EXCEPTION + id)));
    }

    @Override
    public CategoryResponseDto save(CategoryRequestDto requestDto) {
        Category model = categoryMapper.toModel(requestDto);
        return categoryMapper.toDto(categoryRepository.save(model));
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto requestDto) {
        CategoryResponseDto responseDto = getById(id);
        Category categoryUpdate = categoryMapper.toModel(requestDto);
        categoryUpdate.setId(id);
        return categoryMapper.toDto(categoryRepository.save(categoryUpdate));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
