package cybersoft.java16.ecom.product.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import cybersoft.java16.ecom.product.dto.ProductDTO;
import cybersoft.java16.ecom.product.service.ProductService;
@DisplayName("ProductControllerTest")
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser()
public class ProductControllerTest {
	@MockBean
	private ProductService service;
	
	@Autowired
	private MockMvc mockMvc;
	
//	@Test
//	public void shouldSuccessWhenCreateProduct() throws Exception {
//		UUID productID = UUID.randomUUID();
//		ProductDTO product = ProductDTO.builder()
//				.code("Test_product")
//				.description("product for test")
//				.build();
//		
//		when(service.createNewProduct(product)).thenReturn(product);
//		
//		mockMvc.perform(get("/api/v1/product"))
//		.andDo(print())
//		.andExpect(status().isOk());
//	}
	
}
