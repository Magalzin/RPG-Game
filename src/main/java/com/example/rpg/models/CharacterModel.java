package com.example.rpg.models;

import java.util.ArrayList;
import java.util.List;

import com.example.rpg.enums.Classes;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class CharacterModel {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1")
    private Long id;
    @Schema(example = "Magal")
    private String name;
    @Schema(example = "Joaquim o devorador de mundos")
    private String adventurer_name;
    @Schema(example = "WARRIOR")
    private Classes adventurer_class;
    @Schema(example = "3")
    private int level;
    @Schema(example = "10")
    private int strength;
    @Schema(example = "0")
    private int defense;

    @ManyToMany
    @JoinTable(
        name="character_items",
        joinColumns=@JoinColumn(name= "id_character"),
        inverseJoinColumns= @JoinColumn(name= "id_item")

    )
    @Schema(example="")
    private List<ItemModel> magicItems = new ArrayList<>();

    public CharacterModel(){}
    
    public CharacterModel(String name, String adventurer_name, Classes adventurer_class, int level,
            int strength, int defense) {
        this.name = name;
        this.adventurer_name = adventurer_name;
        this.adventurer_class = adventurer_class;
        this.level = level;
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

    public List<ItemModel> getMagicItems() {
        return magicItems;
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

    public void setMagicItems(List<ItemModel> magicItems) {
        this.magicItems = magicItems;
    }

    public void addMagicItem(ItemModel item){
        this.magicItems.add(item);
    }

    public Long getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Character{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", adventurerName='" + this.adventurer_name + '\'' +
                ", characterClass=" + this.adventurer_class +
                ", level=" + this.level +
                ", strength=" + this.strength +
                ", defense=" + this.defense +
                ", magicItems=" + this.magicItems +
                '}';
    }  
}
