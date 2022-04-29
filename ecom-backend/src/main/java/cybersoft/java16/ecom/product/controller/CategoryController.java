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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java16.ecom.common.helper.ResponseHelper;
import cybersoft.java16.ecom.product.dto.CategoryDTO;
import cybersoft.java16.ecom.product.dto.CategoryWithProductsDTO;
import cybersoft.java16.ecom.product.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	@Autowired
	private CategoryService service ;
	
	@GetMapping
	public Object findAllCategoryDTO() {	
		List<CategoryDTO> categoriesDTO = service.findAllCategoryDTO();
		return ResponseHelper.getResponse(categoriesDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public Object createNewCategory(@RequestBody @Valid CategoryDTO dto
									,BindingResult result) {
		if(result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		CategoryDTO newCategory = service.createNewCategory(dto);
		return ResponseHelper.getResponse(newCategory, HttpStatus.OK);
	}
	
	@PostMapping("/add-product/{category-id}/{product-id}")
	public Object addProduct(@PathVariable("category-id") String categoryId
							,@PathVariable("product-id") String productId) {
		CategoryWithProductsDTO modifiedCategory = service.addProduct(categoryId, productId);
		if(modifiedCategory == null) {
			return ResponseHelper.getErrorResponse("Category or Product not found", HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(modifiedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove-product/{category-id}/{product-id}")
	public Object removeProduct(@PathVariable("category-id") String categoryId
								,@PathVariable("product-id") String productId) {
		CategoryWithProductsDTO modifiedCategory = service.removeProduct(categoryId, productId);
		if(modifiedCategory == null) {
			return ResponseHelper.getErrorResponse("Category or Product not found", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseHelper.getResponse(modifiedCategory, HttpStatus.OK);
	}
	
	@GetMapping("/{category-id}")
	public Object findCategoryWithProductsByCategoryId(
			@PathVariable("category-id")String categoryId) {
		CategoryWithProductsDTO categoryWithProductsDTO = service.findCategoryWithProductsByCategoryId(categoryId);
		if(categoryWithProductsDTO == null) {
			return ResponseHelper.getErrorResponse("Category not found", HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(categoryWithProductsDTO, HttpStatus.OK);
	}
}
