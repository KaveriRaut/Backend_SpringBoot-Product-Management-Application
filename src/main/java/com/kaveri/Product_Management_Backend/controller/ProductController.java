package com.kaveri.Product_Management_Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaveri.Product_Management_Backend.model.Product;
import com.kaveri.Product_Management_Backend.service.ProductService;

//specify frontend port url for CORS connection
@CrossOrigin(origins = "http://localhost:3000")
@RestController
//@RequestMapping("/api/")
public class ProductController {
	
	//create @Autowired object of "ProductService" interface to use all Services defined there in this controller
	@Autowired
	private ProductService productService;
	
	//POST req. handling-> data directly send (POST) through "/saveProduct" endpoint to store into DB
	@PostMapping("/saveProduct")
	public ResponseEntity<?> saveProduct(@RequestBody Product myProduct){
		//call the function "saveProductService" of "productService" interface here,and map with "/saveProduct" endpoint
		return new ResponseEntity<>(productService.saveProductService(myProduct), HttpStatus.CREATED);
	}
	
	//GET req. handling-> to fetch all Products from DB
	@GetMapping("/")
	public ResponseEntity<?> getAllProducts(){
		return new ResponseEntity<>(productService.getAllProductsService(),HttpStatus.OK);
	}
	
	//GET req: using 'PathVariable' from url to get {id} and fetch Product with given id
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Integer id){
		try {
			Product tempProduct = productService.getProductByIdService(id);
			if(tempProduct!=null) {
				return new ResponseEntity<>(tempProduct, HttpStatus.OK); 
			}
			else {
				String errorMsg = "Product with ID " + id + " not found.";
				return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			String exptnMsg = "Error while getProductById processing!!";
			return new ResponseEntity<>(exptnMsg, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	//GET req: using 'PathVariable' from url to get {id} and delete the product with that id
	@GetMapping("/deleteProduct/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable Integer id){
		return new ResponseEntity<>(productService.deleteProductService(id), HttpStatus.OK);
	}
	
	//POST req. : give newProduct content and id of oldContent to change->everything logic written in updateProduct Service
	@PostMapping("/updateProduct/{id}")
	public ResponseEntity<?> updateProductById(@RequestBody Product newProduct, @PathVariable Integer id){
		return new ResponseEntity<>(productService.updateProductByIdService(newProduct, id), HttpStatus.CREATED);
	}
	
	
}
