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
import cybersoft.java16.ecom.product.dto.ProductSizeDTO;
import cybersoft.java16.ecom.product.service.ProductSizeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/product-size")
public class ProductSizeController {
	@Autowired
	private ProductSizeService service;
	
	@PostMapping
	public Object createNewProductSize(@RequestBody @Valid ProductSizeDTO dto,
										BindingResult result) {
		if(result.hasErrors()){
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		ProductSizeDTO newSizeDTO = service.createNewProductSize(dto);
		return ResponseHelper.getResponse(newSizeDTO, HttpStatus.OK);
	}
	
	@GetMapping
	public Object findAllProductSize() {
		List<ProductSizeDTO> listSizeDTO = service.findAllProductSize();
		return ResponseHelper.getResponse(listSizeDTO, HttpStatus.OK);
	}
	
	@PutMapping("/update-product-size/{product-size-id}")
	public Object updateProductSize(@PathVariable("product-size-id")String id,
									@RequestBody @Valid ProductSizeDTO dto,
									BindingResult result) {
		if(result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		ProductSizeDTO updatedSizeDTO = service.updateProductSize(id, dto);
		if(updatedSizeDTO == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(updatedSizeDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-product-size/{product-size-id}")
	public Object deleteProductSize(@PathVariable("product-size-id")String id,
									@RequestBody @Valid ProductSizeDTO dto,
									BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		ProductSizeDTO deletedSizeDTO = service.deleteProductSize(id);
		if( deletedSizeDTO == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(deletedSizeDTO, HttpStatus.OK);
	}
	
}
