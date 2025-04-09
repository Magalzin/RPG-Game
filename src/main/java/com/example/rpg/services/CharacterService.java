package com.example.rpg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rpg.models.CharacterModel;
import com.example.rpg.repository.CharacterRepository;

@Service
public class CharacterService {
    @Autowired()
    private CharacterRepository characterRepository;

    public CharacterModel createCharacter(CharacterModel character) {
        return characterRepository.save(character);
    }

    public List<CharacterModel> getAllCharacters() {
        return characterRepository.findAll();
    }
}
