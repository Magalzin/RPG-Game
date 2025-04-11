package com.example.rpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rpg.models.ItemModel;

public interface ItemRepository extends JpaRepository<ItemModel, Long>{
    
}
