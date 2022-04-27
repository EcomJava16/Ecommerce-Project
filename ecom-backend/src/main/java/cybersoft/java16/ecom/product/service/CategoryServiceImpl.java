package cybersoft.java16.ecom.product.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.product.dto.CategoryDTO;
import cybersoft.java16.ecom.product.dto.CategoryWithProductsDTO;
import cybersoft.java16.ecom.product.mapper.CategoryMapper;
import cybersoft.java16.ecom.product.model.Category;
import cybersoft.java16.ecom.product.model.Product;
import cybersoft.java16.ecom.product.repository.CategoryRepository;
import cybersoft.java16.ecom.product.repository.ProductRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository repository;
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<CategoryDTO> findAllCategoryDTO() {
		List<Category> categories = repository.findAll();
		List<CategoryDTO> dtos = categories.stream()
				.map(t -> CategoryMapper.INSTANCE.toDTO(t))
				.collect(Collectors.toList());
		return dtos;
	}

	@Override
	public CategoryDTO createNewCategory(CategoryDTO dto) {
		Category category = CategoryMapper.INSTANCE.toModel(dto);
		Category newCategory = repository.save(category);
		return CategoryMapper.INSTANCE.toDTO(newCategory);
	}
	
	@Override
	public CategoryWithProductsDTO addProduct(String categoryId, String productId) {
		Category category;
		Product product;
		try {
			category = repository.getById(UUID.fromString(categoryId));
			product =productRepository.getById(UUID.fromString(productId));
		} catch (EntityNotFoundException ex) {
			return null;
		}
		category.addProduct(product);
		repository.save(category);
		return CategoryMapper.INSTANCE.toDTOWithProducts(category);
	}

	@Override
	public CategoryWithProductsDTO removeProduct(String categoryId, String productId) {
		Category category;
		Product product;
		try {
			category = repository.getById(UUID.fromString(categoryId));
			product = productRepository.getById(UUID.fromString(productId));
		} catch(EntityNotFoundException ex) {
			return null;
		}
		category.removeProduct(product);
		repository.save(category);	
		return CategoryMapper.INSTANCE.toDTOWithProducts(category);
	}

	@Override
	public CategoryWithProductsDTO findCategoryWithProductsByCategoryId(String categoryId) {
		Category category;
		try {
			category = repository.getById(UUID.fromString(categoryId));
		}catch (EntityNotFoundException ex) {
			return null;
		}
		return CategoryMapper.INSTANCE.toDTOWithProducts(category);
	}
}
