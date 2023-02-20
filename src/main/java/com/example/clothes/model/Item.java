package com.example.clothes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
@Table("Item")
public class Item {
    @Id
    private long id;

    @NotBlank
    private String name;

    @Min(2021)
    private int yearCreated;

    @Min(1000)
    private float price;
    
    private Brand brand;

    public enum Brand {
        Nike("Nike"), Adidas("Addidas"), Gucci("Gucci");
        public String title;
        private Brand(String title){
            this.title = title;
        }
    }
}
