package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "products")
public class Product {
    
	@Id
    @GeneratedValue
	private long id;
    
    @Column(name="name")
    @NotNull
    private String name;
    
    @Column(name="price")
    @NotNull
    private double price;
    			
         public long getId() {
	     return id;
     }
         public void setId(long id) {
	     this.id = id;
     }

         public String getName() {
	     return name;
     }
         public void setName(String name) {
	     this.name = name;
     }

         public double getPrice() {
	     return price;
     }
         public void setPrice(double price) {
	     this.price = price;
     }
		public static Object builder() {
			// TODO Auto-generated method stub
			return null;
		}
}
