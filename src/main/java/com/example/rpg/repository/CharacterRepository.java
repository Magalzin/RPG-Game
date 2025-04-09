package com.example.rpg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rpg.models.CharacterModel;

public interface CharacterRepository extends JpaRepository<CharacterModel, Long> {
}
