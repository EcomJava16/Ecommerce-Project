package cybersoft.java16.ecom.product.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.product.dto.ProductDTO;
import cybersoft.java16.ecom.product.mapper.ProductMapper;
import cybersoft.java16.ecom.product.model.Product;
import cybersoft.java16.ecom.product.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository repository;
	

	@Override
	public List<ProductDTO> findAllProductDTO() {
		return repository.findAll().stream()
				.map(product -> ProductMapper.INSTANCE.toDTO(product))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO createNewProduct(@Valid ProductDTO dto) {
		Product product = ProductMapper.INSTANCE.toModel(dto);
		Product newProduct = repository.save(product);
		return ProductMapper.INSTANCE.toDTO(newProduct);
	}

	@Override
	public ProductDTO findById(String id) {
		Optional<Product> productOpt = repository.findById(UUID.fromString(id));
		if(productOpt.isEmpty()) {
			return null;
		}
		return ProductMapper.INSTANCE.toDTO(productOpt.get());
	}

	@Override
	public ProductDTO updateProduct(String id, @Valid ProductDTO dto) {
		Optional<Product> productOpt = repository.findById(UUID.fromString(id));
		if (productOpt.isEmpty()) {
			return null;
		}
		
		Product product = productOpt.get();
		
		//Check code changed?
		if(product.getCode().equals(dto.getCode())) {
			//Check code is used?
			Optional<Product> existedProduct = repository.findByCode(dto.getCode());
			if(existedProduct.isPresent()) {
				return null;
			}
			product.setCode(dto.getCode());
		}
		
		product.setName(dto.getName());
		
		product.setDescription(dto.getDescription());
		
		repository.save(product);
		
		return ProductMapper.INSTANCE.toDTO(product);
	}

	@Override
	public ProductDTO deleteProductById(String id) {
		Optional<Product> existedProduct = repository.findById(UUID.fromString(id));
		if(existedProduct.isEmpty()) {
			return null;
		}
		repository.deleteById(UUID.fromString(id));
		return ProductMapper.INSTANCE.toDTO(existedProduct.get());
	}

}
