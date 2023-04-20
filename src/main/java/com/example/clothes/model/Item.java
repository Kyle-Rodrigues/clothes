package com.example.clothes.model;


import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank

    private String name;
    @Min(2021)

    private int yearCreated;
    @Min(1000)
    private int price;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = true)
    private DistributionCentre distributionCentre;

    private Brand brand;

    public enum Brand {
        Nike("Nike"), Adidas("Addidas"), Gucci("Gucci");
        public String title;
        private Brand(String title){
            this.title = title;
        }
    }
}
