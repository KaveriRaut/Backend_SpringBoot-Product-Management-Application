package com.kaveri.Product_Management_Backend.service;

import java.util.List;

import com.kaveri.Product_Management_Backend.model.Product;

//This is inteface-> gives declaration of all required service (Prototype) for our Product
public interface ProductService {
	
	//1.to store the object of {Product} class
	public Product saveProductService(Product myProduct);
	
	//2.to get list of all the "Product" objects
	public List<Product> getAllProductsService();
	
	//3.to get the "Product" object with given id
	public Product getProductByIdService(Integer id);
	
	//4.to delete the "Product" with given id
	public String deleteProductService(Integer id);
	
	//5.to update the "Product" with given newProduct and current prod id
	public Product updateProductByIdService(Product newProduct, Integer id);
}
