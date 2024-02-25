package com.kaveri.Product_Management_Backend.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaveri.Product_Management_Backend.model.Product;
import com.kaveri.Product_Management_Backend.repository.ProductRepository;

//this is actual class to define all the services from "ProductService" interface
//define as @Service here
@Service
public class ProductServiceImplemention implements ProductService{

	//create @Autowired object of "ProductRepository" interface to store java class object as into Mysql database table using JPA
	//this object of "ProductRepository" has all the predefined function to carry out (becoz of JPA Jar)
	@Autowired
	private ProductRepository ProductRepo;
	
	@Override
	public Product saveProductService(Product myProduct) {
		return ProductRepo.save(myProduct);
	}

	@Override
	public List<Product> getAllProductsService() {
		return ProductRepo.findAll();
	}

	@Override
	public Product getProductByIdService(Integer id) {
		return ProductRepo.findById(id).get();
	}

	@Override
	public String deleteProductService(Integer id) {
		//first check if Product exist and then delete it
		Product tempProduct = ProductRepo.findById(id).get();
		if(tempProduct!=null) {
			ProductRepo.deleteById(id);
			return "Product deleted successfully.";
		}
		return "Error while deleting the product!!";
	}

	//firstly find {oldProduct} with given {id} and replace its content by the content of {newProduct} that is passed 
	//and save {oldProduct} into DB (by default override if already exist by new content)
	@Override
	public Product updateProductByIdService(Product newProduct, Integer id) {
		//first check if Product exist and then update it
		Product oldProduct = ProductRepo.findById(id).get();
		if(oldProduct!=null) {
			oldProduct.setProductName(newProduct.getProductName());
			oldProduct.setDescription(newProduct.getDescription());
			oldProduct.setPrice(newProduct.getPrice());
			oldProduct.setStatus(newProduct.getStatus());
		}
		return ProductRepo.save(oldProduct);
	}
	
	
}
