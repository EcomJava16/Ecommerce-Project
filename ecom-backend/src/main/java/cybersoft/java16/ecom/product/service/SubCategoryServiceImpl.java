package cybersoft.java16.ecom.product.service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.product.dto.SubCategoryDTO;
import cybersoft.java16.ecom.product.dto.SubCategoryWithProductsDTO;
import cybersoft.java16.ecom.product.mapper.SubCategoryMapper;
import cybersoft.java16.ecom.product.model.Category;
import cybersoft.java16.ecom.product.model.Product;
import cybersoft.java16.ecom.product.model.SubCategory;
import cybersoft.java16.ecom.product.repository.ProductRepository;
import cybersoft.java16.ecom.product.repository.SubCategoryRepository;
import cybersoft.java16.ecom.product.util.ErrorMessage;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	public String errorMessage = "";
	@Autowired
	private SubCategoryRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public SubCategoryDTO creatNewSubCategory(SubCategoryDTO subCategoryDTO) {
		SubCategory newSubCategory = SubCategoryMapper.INSTANCE.toModel(subCategoryDTO);
		repository.save(newSubCategory);
		return SubCategoryMapper.INSTANCE.toDTO(newSubCategory);
	}

	@Override
	public SubCategoryWithProductsDTO findById(String id) {
		Optional<SubCategory> subCategoryOpt;
		try{
			subCategoryOpt = repository.findById(UUID.fromString(id));
			if(subCategoryOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_SUBCATEGORY;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		return SubCategoryMapper.INSTANCE.toDTOWithProducts(subCategoryOpt.get());
	}

	@Override
	public SubCategoryWithProductsDTO addProduct(String subCategoryId, String productId) {
		Optional<SubCategory> subCategoryOpt;
		Optional<Product> productOpt;
		try {
			subCategoryOpt = repository.findById(UUID.fromString(subCategoryId));
			productOpt = productRepository.findById(UUID.fromString(productId));
			if(subCategoryOpt.isEmpty()) { // not found
				errorMessage = ErrorMessage.NOT_FOUND_SUBCATEGORY;
				return null;
			}
			if(productOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_PRODUCT;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		subCategoryOpt.get().addProduct(productOpt.get());
		repository.save(subCategoryOpt.get());
		return SubCategoryMapper.INSTANCE.toDTOWithProducts(subCategoryOpt.get());
	}	

	@Override
	public SubCategoryDTO updateSubCategory(String id, SubCategoryDTO dto) {
		Optional<SubCategory> subCategoryOpt;
		try {
			subCategoryOpt = repository.findById(UUID.fromString(id));
			if(subCategoryOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_SUBCATEGORY;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		SubCategory newSubCategory = subCategoryOpt.get();
		if(newSubCategory.getName() == dto.getName()) {
			errorMessage = ErrorMessage.YEAR_NOT_CHANGE;
			return null;
		}
		newSubCategory.setName(dto.getName());
		newSubCategory.getProducts().stream().forEach(p -> p.setSubCategory(null));
		newSubCategory.setProducts(null);
		return SubCategoryMapper.INSTANCE.toDTO(newSubCategory);
	}	
	
	@Override
	public SubCategoryWithProductsDTO removeProduct(String productId) {
		Optional<Product> productOpt;
		try {
			productOpt = productRepository.findById(UUID.fromString(productId));
			if(productOpt.isEmpty()) {
				errorMessage = ErrorMessage.INVALID_UUID;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		Product product = productOpt.get();
		SubCategory subcategory = productOpt.get().getSubCategoryModel();
		if(subcategory != null) {
			subcategory.removeProduct(product);
			repository.save(subcategory);
		}
		return SubCategoryMapper.INSTANCE.toDTOWithProducts(subcategory);
	}

	@Override
	public SubCategoryDTO deleteById(String id) {
		Optional<SubCategory> subCategoryOpt;
		try {
			subCategoryOpt = repository.findById(UUID.fromString(id));
			if(subCategoryOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_SUBCATEGORY;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		repository.deleteById(UUID.fromString(id));
		return SubCategoryMapper.INSTANCE.toDTO(subCategoryOpt.get());
	}

	@Override
	public SubCategory autoCreateNewSubCategoryWhenAddProductInCategory(Category category, Product product) {
		SubCategory subCategory = SubCategory.builder()
				.name(product.getSubCategory())
				.category(category)
				.products(new LinkedHashSet<Product>())
				.build();
		return subCategory;
	}
	
	@Override
	public String getErrorMessage() {
		return errorMessage;
	}
}
