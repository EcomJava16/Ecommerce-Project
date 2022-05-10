package cybersoft.java16.ecom.product.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.product.dto.ProductDTO;
import cybersoft.java16.ecom.product.dto.ProductUpdateDTO;
import cybersoft.java16.ecom.product.mapper.ProductMapper;
import cybersoft.java16.ecom.product.model.Product;
import cybersoft.java16.ecom.product.repository.ProductRepository;
import cybersoft.java16.ecom.product.util.ErrorMessage;

@Service
public class ProductServiceImpl implements ProductService {
	private String errorMessage = "";
	
	@Autowired
	private ProductRepository repository;
	
	@Override
	public ProductDTO createNewProduct(ProductDTO dto) {
		Product product = ProductMapper.INSTANCE.toModel(dto);
		Product newProduct = repository.save(product);
		return ProductMapper.INSTANCE.toDTO(newProduct);
	}
	
	@Override
	public List<ProductDTO> findAllProductDTO() {
		return repository.findAll().stream()
				.map(product -> ProductMapper.INSTANCE.toDTO(product))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO findById(String id) {
		Optional<Product> productOpt;
		// catch invalid and not found
		try {
			productOpt = repository.findById(UUID.fromString(id));
			if(productOpt.isEmpty()) { // not found
				errorMessage = ErrorMessage.NOT_FOUND_PRODUCT;
				return null;
			}
		} catch (IllegalArgumentException ex) { // invalid
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		return ProductMapper.INSTANCE.toDTO(productOpt.get());
	}

	@Override
	public ProductDTO findByCode(String code) {
		Optional<Product> productOpt;
		try{
			productOpt = repository.findByCode(code);
			if(productOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_PRODUCT;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		return ProductMapper.INSTANCE.toDTO(productOpt.get());
	}

	@Override
	public ProductDTO findByName(String name) {
		Optional<Product> productOpt;
		try{
			productOpt = repository.findByName(name);
			if(productOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_PRODUCT;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		return ProductMapper.INSTANCE.toDTO(productOpt.get());
	}

	@Override
	public ProductUpdateDTO updateProduct(String id,ProductUpdateDTO dto) {
		Optional<Product> productOpt;
		try{
			productOpt = repository.findById(UUID.fromString(id));
			if (productOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_PRODUCT;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		
		Product product = productOpt.get();
		
		//Check code changed?
		if(!(product.getCode().equals(dto.getCode()))) {
			//Check code is used?
			Optional<Product> existedProduct = repository.findByCode(dto.getCode());
			if(existedProduct.isPresent()) {
				errorMessage = ErrorMessage.CODE_IS_USED;
				return null;
			}
			product.setCode(dto.getCode());			
		}	
		product.setName(dto.getName());	
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		repository.save(product);
		
		return ProductMapper.INSTANCE.toProductUpdateDTO(product);
	}

	@Override
	public ProductDTO deleteProductById(String id) {
		Optional<Product> existedProductOpt;
		try{
			existedProductOpt = repository.findById(UUID.fromString(id));
			if(existedProductOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_PRODUCT;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		repository.deleteById(UUID.fromString(id));
		return ProductMapper.INSTANCE.toDTO(existedProductOpt.get());
	}
	
	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

}
