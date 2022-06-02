package com.example.demo.RepositoryTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositorytest {
    @Autowired
    private ProductRepository productRepository;
    private Product product;
    @BeforeEach
    public void setUp() {
        product = new Product("Bat",2500);
    }
    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        product = null;
    }
    
    @Test
    public void givenProductToAddShouldReturnAddedProduct(){
         productRepository.save(product);
         Product fetchedProduct = productRepository.findById(product.getId()).get();
         assertEquals(1, fetchedProduct.getId());
    }
}
