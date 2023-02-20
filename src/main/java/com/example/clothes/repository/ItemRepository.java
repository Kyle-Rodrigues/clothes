package com.example.clothes.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.clothes.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
    
}
