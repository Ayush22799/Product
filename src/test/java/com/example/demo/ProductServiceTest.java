package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    
	@InjectMocks
    private ProductRepository productRepository;

    @Mock
    private ProductServiceImpl productService;

    private Product product;
	@DisplayName("JUnit test for saveProduct method")
	@Test
	public void savedProductSuccess() {
		Product product = new Product();
		product.setName("Product1");
		product.setPrice(32000);
        

        // providing knowledge
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct.getName()).isNotNull();
        assertThat(savedProduct.getPrice()).isNotNull();
	}
}
