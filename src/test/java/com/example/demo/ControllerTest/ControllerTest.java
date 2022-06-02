package com.example.demo.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.ProductController;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

public class ControllerTest {
	 //unit test
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;
    private Product product;
   private List<Product> productList;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup(){
        product = new Product("ball",670);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @AfterEach
    void tearDown() {
        product = null;
    }

    @Test
    public void PostMappingOfProduct() throws Exception{
        when(productService.createProduct(any())).thenReturn(product);
        mockMvc.perform((RequestBuilder) ((ResultActions) post("/product")).
                andExpect(status().isCreated()));
        verify(productService,times(1)).createProduct(any());
    }

    @Test
    public void GetMappingOfAllProduct() throws Exception {
        when(productService.getAllProduct()).thenReturn(productList);
        mockMvc.perform((RequestBuilder) ((ResultActions) MockMvcRequestBuilders.get("/products")).
                andDo(MockMvcResultHandlers.print()));
        verify(productService).getAllProduct();
        verify(productService,times(1)).getAllProduct();
    }

    @Test
    public void GetMappingOfProductShouldReturnRespectiveProducct() throws Exception {
        when(productService.getProductById(product.getId())).thenReturn(product);
        mockMvc.perform((RequestBuilder) ((ResultActions) get("/product/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()));
    }

    @Test
    public void DeleteMappingUrlAndIdThenShouldReturnDeletedProduct() throws Exception {
        when(productService.deleteProduct(product.getId())).thenReturn(product);
        mockMvc.perform((RequestBuilder) ((ResultActions) delete("/product/1"))
               .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()));
    }

    

}

