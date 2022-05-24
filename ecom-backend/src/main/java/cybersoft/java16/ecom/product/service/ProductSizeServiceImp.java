package cybersoft.java16.ecom.product.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.product.dto.ProductSizeDTO;
import cybersoft.java16.ecom.product.mapper.ProductSizeMapper;
import cybersoft.java16.ecom.product.model.ProductSize;
import cybersoft.java16.ecom.product.repository.ProductSizeRepository;
import cybersoft.java16.ecom.product.util.ErrorMessage;

@Service
public class ProductSizeServiceImp implements ProductSizeService {
	private String errorMessage = "";
	
	@Autowired
	private ProductSizeRepository repository;
	
	@Override
	public ProductSizeDTO createNewProductSize(ProductSizeDTO dto) {
		ProductSize size = ProductSizeMapper.INSTANCE.toModel(dto);
		ProductSize newSize = repository.save(size); 
		return ProductSizeMapper.INSTANCE.toDTO(newSize);
	}

	@Override
	public List<ProductSizeDTO> findAllProductSize() {
		List<ProductSize> listSize = repository.findAll();
		List<ProductSizeDTO> listSizeDTO = listSize
				.stream()
				.map(s -> ProductSizeMapper.INSTANCE.toDTO(s))
				.collect(Collectors.toList());
		return listSizeDTO;
	}

	@Override
	public ProductSizeDTO updateProductSize(String id,ProductSizeDTO dto) {
		Optional<ProductSize> sizeOpt;
		try{
			sizeOpt = repository.findById(UUID.fromString(id));
			if(sizeOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_PRODUCT_SIZE;
				return null;
			}
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		ProductSize size = sizeOpt.get();
		size.setName(dto.getName());
		return ProductSizeMapper.INSTANCE.toDTO(size);
	}

	@Override
	public ProductSizeDTO deleteProductSize(String id) {
		Optional<ProductSize> sizeOpt; 
		try {
			sizeOpt = repository.findById(UUID.fromString(id));
			if(sizeOpt.isEmpty()) {
				errorMessage = ErrorMessage.NOT_FOUND_PRODUCT_SIZE;
				return null;
			}		
		}catch(IllegalArgumentException ex) {
			errorMessage = ErrorMessage.INVALID_UUID;
			return null;
		}
		ProductSize size = sizeOpt.get();
		repository.delete(size);
		return ProductSizeMapper.INSTANCE.toDTO(size);
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

}
