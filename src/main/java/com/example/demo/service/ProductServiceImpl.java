package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
       
	private ProductRepository productRepository;
	
	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
    @Override   
	public Product createProduct(Product product) {
    	   return productRepository.save(product);
       }
       
    @Override
       public Product updateProduct(Product product) {
    	   
    	Optional<Product> postgres = this.productRepository.findById(product.getId());
    	if(postgres.isPresent()) {
    	Product productUpdate = postgres.get();
    	System.out.println(productUpdate);
    	productUpdate.setId(product.getId());
    	productUpdate.setName(product.getName());
    	productUpdate.setPrice(product.getPrice());
    	Product productSaved = productRepository.save(productUpdate);
    	return productSaved;
       }else {
    	   throw new ResourceNotFoundException("Record Not Found with id "+ product.getId());
       }
    }
    @Override
    public Product getProductById(long productId) {
		Optional<Product> postgres = this.productRepository.findById(productId);
		if(postgres.isPresent()) {
			return postgres.get();
			} 
		else {
			throw new ResourceNotFoundException("Record Not Found with id "+ productId);
		}
    }	
    	@Override
    	public Product deleteProduct(long productId) {
    		Product product = null;
    		Optional<Product> postgres = this.productRepository.findById(productId);
    		if(postgres.isPresent()) {
    			this.productRepository.delete(postgres.get());
    		}
    		return product;
    		
    	}

		@Override
		public List<Product> getAllProduct() {
			// TODO Auto-generated method stub
			return null;
		}
    	
}
