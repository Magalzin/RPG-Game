package com.example.rpg.models;


import java.util.ArrayList;
import java.util.List;

import com.example.rpg.enums.ItemType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(example = "1")
    private Long id;
    @Schema(example = "AMULET")
    private ItemType type;
    @Schema(example = "Amuleto do devorador")
    private String name;
    @Schema(example = "10")
    private int extraStrength;
    @Schema(example = "10")
    private int extraDefense;
    

    public ItemModel(){}

    public ItemModel(int extraDefense, int extraStrength, String name, ItemType type) {
        this.extraDefense = extraDefense;
        this.extraStrength = extraStrength;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExtraStrength() {
        return extraStrength;
    }

    public void setExtraStrength(int extraStrength) {
        this.extraStrength = extraStrength;
    }

    public int getExtraDefense() {
        return extraDefense;
    }

    public void setExtraDefense(int extraDefense) {
        this.extraDefense = extraDefense;
    }

}
