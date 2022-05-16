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
//	@Mock
//	private ProductRepository repository;
//	
//	@Mock
//	private ProductMapper mapper;
//	
//	@InjectMocks
//	private ProductService service = new ProductServiceImpl();
//	
//	@Test
//	public void shouldMockCorrectly () {
//		UUID productID = UUID.randomUUID();
//		Product product = Product.builder()
//				.id(productID)
//				.code("Test_product")
//				.description("product for test")
//				.build();
//	when(repository.findById(productID)).thenReturn(Optional.ofNullable(product));
//	Optional<Product> actualProduct = repository.findById(productID);
//	assertEquals(productID, actualProduct.get().getId());
//	assertEquals("Test_product", actualProduct.get().getCode());
//	assertEquals("product for test", actualProduct.get().getDescription());
//	}
//	
//	@Test
//	public void shouldReturnEmptyListWhenNoEntityFound() {
//		when(repository.findAll()).thenReturn(List.of());
//		
//		List<ProductDTO> products = service.findAllProductDTO();
//		assertEquals(0, products.size());
//	}
//	
//	@Test
//	public void shouldReturnAProductWhenCreateNewProduct() {
//		UUID productID = UUID.randomUUID();
//		Product product = Product.builder()
//				.id(productID)
//				.code("Test_product")
//				.description("product for test")
//				.build();
//		
//	}
}
