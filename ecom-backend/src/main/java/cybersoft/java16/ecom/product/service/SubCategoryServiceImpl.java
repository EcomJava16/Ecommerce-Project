package cybersoft.java16.ecom.product.service;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.product.dto.ProductDTO;
import cybersoft.java16.ecom.product.dto.SubCategoryDTO;
import cybersoft.java16.ecom.product.dto.SubCategoryWithProductsDTO;
import cybersoft.java16.ecom.product.mapper.SubCategoryMapper;
import cybersoft.java16.ecom.product.model.Product;
import cybersoft.java16.ecom.product.model.SubCategory;
import cybersoft.java16.ecom.product.repository.ProductRepository;
import cybersoft.java16.ecom.product.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
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
		Optional<SubCategory> subCategory = repository.findById(UUID.fromString(id));
		if(subCategory.isEmpty()) {
			return null;
		}
		return SubCategoryMapper.INSTANCE.toDTOWithProducts(subCategory.get());
	}

	@Override
	public SubCategoryWithProductsDTO addProductDTO(String subCategoryId, String productId) {
		SubCategory subCategory;
		Product product;
		try {
			subCategory = repository.getById(UUID.fromString(subCategoryId));
			product = productRepository.getById(UUID.fromString(productId));
		}catch(EntityNotFoundException ex) {
			return null;
		}
		subCategory.addProduct(product);
		repository.save(subCategory);
		return SubCategoryMapper.INSTANCE.toDTOWithProducts(subCategory);
	}

}
