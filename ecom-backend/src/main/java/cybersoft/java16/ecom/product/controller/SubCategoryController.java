package cybersoft.java16.ecom.product.controller;

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
import cybersoft.java16.ecom.product.dto.SubCategoryDTO;
import cybersoft.java16.ecom.product.dto.SubCategoryWithProductsDTO;
import cybersoft.java16.ecom.product.service.SubCategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/subCategory")
public class SubCategoryController {
	@Autowired
	private SubCategoryService service;
	
	@PostMapping("/create")
	public Object createNewSubCategory(@RequestBody SubCategoryDTO subCategoryDTO
										,BindingResult result) {
		if(result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		SubCategoryDTO newSubCategory = service.creatNewSubCategory(subCategoryDTO);
		return ResponseHelper.getResponse(newSubCategory, HttpStatus.OK);
	}
	
	@GetMapping("/{subcategory-id}")
	public Object findById(@PathVariable("subcategory-id") String id) {
		SubCategoryWithProductsDTO subCategoryWithProductsDTO = service.findById(id);
		if(subCategoryWithProductsDTO == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(subCategoryWithProductsDTO, HttpStatus.OK);
	}
	
	@PostMapping("/{subcategory-id}/{product-id}")
	public Object addProduct(@PathVariable("subcategory-id") String subcategoryId
								,@PathVariable("product-id") String productId) {
		SubCategoryWithProductsDTO subCategoryWithProductsDTO = service.addProduct(subcategoryId, productId);
		if (subCategoryWithProductsDTO == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(subCategoryWithProductsDTO, HttpStatus.OK);
	}
	
	@PostMapping("/update/{subcategory-id}")
	public Object updateSubCategory(@PathVariable("subcategory-id") String id
									,@RequestBody @Valid SubCategoryDTO dto
									,BindingResult result) {
		if(result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		SubCategoryDTO updatedSubCategory = service.updateSubCategory(id, dto);
		if(updatedSubCategory == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(updatedSubCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{product-id}")
	public Object removeProduct(@PathVariable("product-id") String productId) {
		SubCategoryWithProductsDTO subCategoryWithProductsDTO = service.removeProduct(productId);
		if(subCategoryWithProductsDTO == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.OK);
		}
		return ResponseHelper.getResponse(subCategoryWithProductsDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{subcategory-id}")
	public Object deleteById(@PathVariable("subcategory-id") String id) {
		SubCategoryDTO subCategoryDTO = service.deleteById(id);
		if(subCategoryDTO == null) {
			return ResponseHelper.getErrorResponse(service.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(subCategoryDTO, HttpStatus.OK);
	}
}
