package com.Projects.MedStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.Projects.MedStore.Model.Product;
import com.Projects.MedStore.repository.ProductRepository;


@Service  
public class ProductService {

	@Autowired  
	ProductRepository productRepository;  
	
	public void addProduct(Product product) {
		
		productRepository.save(product);
		
		
		
	}

    public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
    }

    public void deleteProduct(Product product) {
		productRepository.delete(product);

    }

	public Optional<Product> getProductById(String productId) {
		return productRepository.findById(productId);
	}

	public void updateProduct(Product product) {
		productRepository.save(product);
	}
	public boolean existsById(String productId) {
		return productRepository.existsById(productId);
	}


}
