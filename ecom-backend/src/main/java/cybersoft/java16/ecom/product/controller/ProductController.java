package cybersoft.java16.ecom.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java16.ecom.common.helper.ResponseHelper;
import cybersoft.java16.ecom.product.dto.ProductDTO;
import cybersoft.java16.ecom.product.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping
	public Object findAllProductDTO() {
		List<ProductDTO> productsDTO = service.findAllProductDTO(); 
		return ResponseHelper.getResponse(productsDTO, HttpStatus.OK);	
	}
	
	@PostMapping
	public Object createNewProduct(@Valid @RequestBody ProductDTO dto
							, BindingResult result ) {
		if(result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		
		ProductDTO newProduct = service.createNewProduct(dto);
		return ResponseHelper.getResponse(newProduct, HttpStatus.OK);
	}
	
	@GetMapping("/{product-id}")
	public Object findById(@PathVariable("product-id") String id ) {
		ProductDTO product = service.findById(id);
		if(product == null) {
			return ResponseHelper.getErrorResponse("Not found product", HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(product, HttpStatus.OK);
	}
	
	@PutMapping("/{product-id}")
	public Object updateProduct(@PathVariable("product-id") String id
								,@Valid @RequestBody ProductDTO dto
								,BindingResult result) {
		if(result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		ProductDTO updateProduct = service.updateProduct(id, dto);
		
		if(updateProduct == null) {
			return ResponseHelper.getErrorResponse("Product is not exist or product code already used", HttpStatus.BAD_REQUEST);
		}	
		return ResponseHelper.getResponse(updateProduct, HttpStatus.OK);
	}
	
	@DeleteMapping("/{product-id}")
	public Object deleteProduct(@PathVariable("product-id") String id) {
		ProductDTO deleteProduct = service.deleteProductById(id);
		if(deleteProduct == null) {
			return ResponseHelper.getErrorResponse("Product is not exist", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseHelper.getResponse(deleteProduct, HttpStatus.OK);
	}
}
