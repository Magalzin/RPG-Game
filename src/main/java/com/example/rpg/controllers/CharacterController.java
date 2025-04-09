package com.example.rpg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rpg.models.CharacterModel;
import com.example.rpg.services.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public List<CharacterModel> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @PostMapping
    public CharacterModel createCharacter(@RequestBody CharacterModel character) {
        return characterService.createCharacter(character);
    }
    
}
