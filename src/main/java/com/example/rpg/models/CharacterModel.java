package com.example.rpg.models;

import com.example.rpg.enums.Classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CharacterModel {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String adventurer_name;
    private Classes adventurer_class;
    private int level;
    private String magicItems;
    private int strength;
    private int defense;
    
    public CharacterModel(String name, String adventurer_name, Classes adventurer_class, int level, String magicItems,
            int strength, int defense) {
        this.name = name;
        this.adventurer_name = adventurer_name;
        this.adventurer_class = adventurer_class;
        this.level = level;
        this.magicItems = magicItems;
        this.strength = strength;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdventurer_name() {
        return adventurer_name;
    }

    public void setAdventurer_name(String adventurer_name) {
        this.adventurer_name = adventurer_name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMagicItems() {
        return magicItems;
    }

    public void setMagicItems(String magicItems) {
        this.magicItems = magicItems;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Classes getAdventurer_class() {
        return adventurer_class;
    }

    public void setAdventurer_class(Classes adventurer_class) {
        this.adventurer_class = adventurer_class;
    }
    

}
