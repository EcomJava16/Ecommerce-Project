package cybersoft.java16.ecom.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.product.dto.CategoryDTO;
import cybersoft.java16.ecom.product.dto.CategoryWithSubCategoriesDTO;
import cybersoft.java16.ecom.product.mapper.CategoryMapper;
import cybersoft.java16.ecom.product.model.Category;
import cybersoft.java16.ecom.product.model.Product;
import cybersoft.java16.ecom.product.model.SubCategory;
import cybersoft.java16.ecom.product.repository.CategoryRepository;
import cybersoft.java16.ecom.product.repository.ProductRepository;
import cybersoft.java16.ecom.product.repository.SubCategoryRepository;
import cybersoft.java16.ecom.product.util.ErrorMessage;

@Service
public class CategoryServiceImpl implements CategoryService {
	private String errorMessage = "" ;
	
	@Autowired
	private CategoryRepository repository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Override
	public CategoryDTO createNewCategory(CategoryDTO dto) {
		Category category = CategoryMapper.INSTANCE.toModel(dto);
		Category newCategory = repository.save(category);
		return CategoryMapper.INSTANCE.toDTO(newCategory);
	}
	
	@Override
	public List<CategoryWithSubCategoriesDTO> findAllCategoryDTO() {
		List<Category> categories = repository.findAll();
		return categories.stream()
				.map(t -> CategoryMapper.INSTANCE.toDTOWithSubCategories(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public CategoryWithSubCategoriesDTO findCategoryWithSubCategoryByCategoryId(String categoryId) {
		Optional<Category> categoryOpt;
		try {
			categoryOpt = repository.findById(UUID.fromString(categoryId));
			if(categoryOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_CATEGORY;
				return null;
			}
		}catch(IllegalArgumentException ex){
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		return CategoryMapper.INSTANCE.toDTOWithSubCategories(categoryOpt.get());
	}
	
	@Override
	public CategoryDTO updateCategory(String id, CategoryDTO dto) {
		Optional<Category> categoryOpt;
		try {
			categoryOpt = repository.findById(UUID.fromString(id));
			if(categoryOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_CATEGORY;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		Category category = categoryOpt.get();
		category.setName(dto.getName());
		repository.save(category);
		return CategoryMapper.INSTANCE.toDTO(category);
	}

	@Override
	public CategoryWithSubCategoriesDTO addSubCategory(String categoryId, String subCategoryId) {
		Optional<Category> categoryOpt;
		Optional<SubCategory> subCategoryOpt;
		try {
			categoryOpt = repository.findById(UUID.fromString(categoryId));
			subCategoryOpt = subCategoryRepository.findById(UUID.fromString(subCategoryId));
			if(categoryOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_CATEGORY;
				return null;
			}
			if(subCategoryOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_SUBCATEGORY;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		categoryOpt.get().addSubCategory(subCategoryOpt.get());
		repository.save(categoryOpt.get());
		return CategoryMapper.INSTANCE.toDTOWithSubCategories(categoryOpt.get());
	}

	@Override
	public List<CategoryWithSubCategoriesDTO> removeProduct(String productId) {
		Optional<Product> productOpt;
		try {
			productOpt = productRepository.findById(UUID.fromString(productId));
			if(productOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_PRODUCT;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		// remove product from subCategory
		List<Category> categories = new ArrayList<Category>();
		if(productOpt.get().getSubCategory() != null) {
			SubCategory subCategory = productOpt.get().getSubCategoryModel();
			subCategory.removeProduct(productOpt.get());
			subCategoryRepository.save(subCategory);
			if(subCategory.getCategory() != null) {
				categories.add(subCategory.getCategory());
			}else {
				categories = repository.findAll();	
			}
		}else {
			categories = repository.findAll();			
		}
		return categories.stream()
			.map(t -> CategoryMapper.INSTANCE.toDTOWithSubCategories(t))
			.collect(Collectors.toList());	
	}
	
	@Override
	public CategoryDTO deleteCategory(String id) {
		Optional<Category> categoryOpt;
		try {
			categoryOpt = repository.findById(UUID.fromString(id));
			if(categoryOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_CATEGORY;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		repository.deleteById(UUID.fromString(id));
		return CategoryMapper.INSTANCE.toDTO(categoryOpt.get());
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}
}
