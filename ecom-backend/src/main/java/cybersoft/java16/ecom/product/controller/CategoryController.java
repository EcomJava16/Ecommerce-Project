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
import cybersoft.java16.ecom.product.dto.CategoryWithSubCategoriesDTO;
import cybersoft.java16.ecom.product.service.CategoryService;

@CrossOrigin(origins = {"http://localhost:3000","https://frontendjava16.herokuapp.com"})
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	@Autowired
	private CategoryService service ;
	
	@PostMapping
	public Object createNewCategory(@RequestBody @Valid CategoryDTO dto
									,BindingResult result) {
		if(result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		CategoryDTO newCategory = service.createNewCategory(dto);
		return ResponseHelper.getResponse(newCategory, HttpStatus.OK);
	}
		
	@GetMapping
	public Object findAllCategoryDTO() {	
		List<CategoryWithSubCategoriesDTO> categoriesDTO = service.findAllCategoryDTO();
		return ResponseHelper.getResponse(categoriesDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{category-id}")
	public Object findCategoryWithSubCategoryByCategoryId(
			@PathVariable("category-id")String categoryId) {
		CategoryWithSubCategoriesDTO categoryWithSubCategoryDTO = service.findCategoryWithSubCategoryByCategoryId(categoryId);
		if(categoryWithSubCategoryDTO == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(categoryWithSubCategoryDTO, HttpStatus.OK);
	}
	
	@PostMapping("/update/{category-id}")
	public Object updateCategory(@PathVariable("category-id") String id
								,@RequestBody @Valid CategoryDTO dto
								,BindingResult result) {
		if(result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		CategoryDTO updatedCategoryDTO = service.updateCategory(id, dto);
		if(updatedCategoryDTO == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(updatedCategoryDTO, HttpStatus.OK);
	}
	
	
	@PostMapping("/add-subcategory/{category-id}/{subcategory-id}")
	public Object addSubCategory(@PathVariable("category-id") String categoryId
								,@PathVariable("subcategory-id") String subCategoryId) {
		CategoryWithSubCategoriesDTO modifiedCategory = service.addSubCategory(categoryId, subCategoryId);
		if(modifiedCategory == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(modifiedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove-product/{product-id}")
	public Object removeProduct(@PathVariable("product-id") String productId) {
		List<CategoryWithSubCategoriesDTO> categories = service.removeProduct(productId);
		if(categories == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(categories, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-category/{category-id}")
	public Object deleteCategory(@PathVariable("category-id") String id) {
		CategoryDTO deletedCategory = service.deleteCategory(id);
		if(deletedCategory == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(deletedCategory, HttpStatus.OK);
	}
}
