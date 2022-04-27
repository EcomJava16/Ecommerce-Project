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
	public ProductDTO createNewProduct(ProductDTO dto) {
		Product product = ProductMapper.INSTANCE.toModel(dto);
		Product newProduct = repository.save(product);
		return ProductMapper.INSTANCE.toDTO(newProduct);
	}

	@Override
	public ProductDTO findById(String id) {
		UUID uuid;
		try {
			uuid = UUID.fromString(id);
		} catch (IllegalArgumentException ex) {
			return null;
		}
		Optional<Product> productOpt = repository.findById(uuid);
		if(productOpt.isEmpty()) {
			return null;
		}
		return ProductMapper.INSTANCE.toDTO(productOpt.get());
	}

	@Override
	public ProductUpdateDTO updateProduct(String id,ProductUpdateDTO dto) {
		Optional<Product> productOpt = repository.findById(UUID.fromString(id));
		if (productOpt.isEmpty()) {
			return null;
		}
		
		Product product = productOpt.get();
		
		//Check code changed?
		if(!(product.getCode().equals(dto.getCode()))) {
			//Check code is used?
			Optional<Product> existedProduct = repository.findByCode(dto.getCode());
			if(existedProduct.isPresent()) {
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
		Optional<Product> existedProduct = repository.findById(UUID.fromString(id));
		if(existedProduct.isEmpty()) {
			return null;
		}
		repository.deleteById(UUID.fromString(id));
		return ProductMapper.INSTANCE.toDTO(existedProduct.get());
	}

	@Override
	public ProductDTO findByCode(String code) {
		Optional<Product> product = repository.findByCode(code);
		if(product.isEmpty()) {
			return null;
		}
		return ProductMapper.INSTANCE.toDTO(product.get());
	}

	@Override
	public ProductDTO findByName(String name) {
		Optional<Product> product = repository.findByName(name);
		if(product.isEmpty()) {
			return null;
		}
		return ProductMapper.INSTANCE.toDTO(product.get());
	}

}
