package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    
	@Mock
    private ProductRepository productRepository;

    @InjectMocks
    @Autowired
    private ProductServiceImpl productService;
    private Product product1;
    private Product product2;
    List<Product> productList;


   
    @BeforeEach
    public void setUp() {
    productList = new ArrayList<>();
    product1 = new Product(1, "bread",20);
    product2 = new Product(2, "jam",200);
    productList.add(product1);
    productList.add(product2);
    }
    
    @AfterEach
    public void tearDown() {
    product1 = product2 = null;
    productList = null;
    }
	
    @Test
    public void GivenGetAllUsersShouldReturnListOfAllUsers(){
         productRepository.save(product1);
        //stubbing mock to return specific data
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> productList1 =productService.getAllProduct();
        assertEquals(productList1,productList);
        verify(productRepository,times(1)).save(product1);
        verify(productRepository,times(1)).findAll();
    }
}
