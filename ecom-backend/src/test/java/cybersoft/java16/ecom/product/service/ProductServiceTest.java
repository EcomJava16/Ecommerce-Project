package cybersoft.java16.ecom.product.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import cybersoft.java16.ecom.product.dto.ProductDTO;
import cybersoft.java16.ecom.product.mapper.ProductMapper;
import cybersoft.java16.ecom.product.model.Product;
import cybersoft.java16.ecom.product.repository.ProductRepository;

@DisplayName("Product Service")
@SpringBootTest
public class ProductServiceTest {
	@Mock
	private ProductRepository repository;
	
	@Mock
	private ProductMapper mapper;
	
	@InjectMocks
	private ProductService service = new ProductServiceImpl();
	
	@Test
	public void shouldMockCorrectly () {
		UUID productID = UUID.randomUUID();
		Product product = Product.builder()
				.id(productID)
				.code("Test_product")
				.description("product for test")
				.build();
	when(repository.findById(productID)).thenReturn(Optional.ofNullable(product));
	Product actualProduct = repository.findById(productID).get();
	assertEquals(productID, actualProduct.getId());
	assertEquals("Test_product", actualProduct.getCode());
	assertEquals("product for test", actualProduct.getDescription());
	}
	
	@Test
	public void shouldReturnAllEntityCorrectlyWhenNoEntityFound() {
		when(repository.findAll()).thenReturn(List.of());
		
		List<ProductDTO> products = service.findAllProductDTO();
		assertEquals(0, products.size());
	}
	
	@Test
	public void shouldReturnCorrectlyWhenCreate() {
		UUID productID = UUID.randomUUID();
		Product product = Product.builder()
				.id(productID)
				.code("Test_product")
				.description("product for test")
				.build();
		
	}
}
