package com.example.rpg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rpg.DTOs.AdventurerNameDto;
import com.example.rpg.models.CharacterModel;
import com.example.rpg.services.CharacterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    @Operation(summary = "Retorna todos os personagens", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterModel.class))))
    public ResponseEntity<Object> getAllCharacters() {
        try {
            return ResponseEntity.ok(characterService.getAllCharacters());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um personagem pelo identificador", parameters = @Parameter(required = true, name = "id", example = "1"), responses = @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterModel.class))))
    public ResponseEntity<Object> getCharacter(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(characterService.getCharacterById(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Cria um personagem", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Exemplo de personagem", summary = "Joaquim o guerreiro devorador", value = "{\"name\": \"Magal\", \"adventurer_name\": \"Joaquim\", \"adventurer_class\": \"WARRIOR\", \"level\": \"10\", \"strength\": \"10\", \"defense\": \"0\"}"
    // [
    // {
    // "id": 1,
    // "name": "sla",
    // "adventurer_name": "SuperSla",
    // "adventurer_class": "WARRIOR",
    // "level": 10,
    // "strength": 8,
    // "defense": 2,
    // }
    // ]
    ))), responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{ \"id\": \"1\", \"name\": \"Magal\", \"adventurer_name\": \"Joaquim\", \"adventurer_class\": \"WARRIOR\", \"level\": \"10\", \"strength\": \"10\", \"defense\": \"0\", \"magicItems\": \"[]\"}"))))
    public ResponseEntity<Object> createCharacter(@RequestBody CharacterModel character) {
        try {
            return ResponseEntity.ok(characterService.createCharacter(character));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Atualiza o nome de um aventureiro", parameters = @Parameter(required = true, name = "id", example = "1"), requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Exemplo de mudança", summary = "Nao é mais joaquim é cleuso", value = "{\"adventurerName\": \"cleuso\"}"))), responses= @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{ \"id\": \"1\", \"name\": \"Magal\", \"adventurer_name\": \"Joaquim\", \"adventurer_class\": \"WARRIOR\", \"level\": \"10\", \"strength\": \"10\", \"defense\": \"0\", \"magicItems\": \"[]\"}"))))
    public ResponseEntity<Object> updateAdventurerName(@PathVariable Long id,
            @RequestBody AdventurerNameDto adventurerName) {
        try {
            CharacterModel updatedCharacter = characterService.updateAdventurerNameById(id,
                    adventurerName.adventurerName());
            return ResponseEntity.ok(updatedCharacter);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um aventureiro", parameters = @Parameter(required = true, name = "id", example = "1"), responses= @ApiResponse(responseCode = "204", content = @Content(mediaType = "application/json", examples=@ExampleObject("{}"))))
    public ResponseEntity<Object> deleteAdventurer(@PathVariable Long id) {
        try {
            characterService.deleteCharacter(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("items/add-in/{idItem}/{idCharacter}")
    @Operation(summary = "Adiciona um item ao personagem", parameters = {@Parameter(required = true, name = "idItem", example = "1"),@Parameter(required = true, name = "idCharacter", example = "1")}, responses= @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{ \"id\": \"1\", \"name\": \"Magal\", \"adventurer_name\": \"Joaquim\", \"adventurer_class\": \"WARRIOR\", \"level\": \"10\", \"strength\": \"10\", \"defense\": \"0\", \"magicItems\": \"[]\"}"))))
    public ResponseEntity<Object> addItemInCharacter(@PathVariable Long idItem, @PathVariable Long idCharacter) {
        try {
            return ResponseEntity.ok(characterService.addItemInCharacter(idItem, idCharacter));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("items/remove-from/{idItem}/{idCharacter}")
    @Operation(summary = "Deleta um item do aventureiro", parameters = {@Parameter(required = true, name = "idItem", example = "1"),@Parameter(required = true, name = "idCharacter", example = "1")}, responses= @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples=@ExampleObject(value = "{ \"id\": \"1\", \"name\": \"Magal\", \"adventurer_name\": \"Joaquim\", \"adventurer_class\": \"WARRIOR\", \"level\": \"10\", \"strength\": \"10\", \"defense\": \"0\", \"magicItems\": \"[]\"}"))))
    public ResponseEntity<Object> removeItemFromCharacter(@PathVariable Long idItem, @PathVariable Long idCharacter) {
        try {
            return ResponseEntity.ok(characterService.removeItemFromCharacter(idItem, idCharacter));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/items/{id}")
    @Operation(summary = "Retorna todos os itens do aventureiro", parameters = @Parameter(required = true, name = "id", example = "1"), responses= @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples=@ExampleObject(value = "[{\"id\": \"1\", \"type\": \"AMULET\", \"name\": \"amuleto do devorador\", \"extraStrenght\": \"10\", \"extraDefense\": \"10\"},]"))))
    public ResponseEntity<Object> getAllItemsInCharacter(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(characterService.getAllItemsFromCharacter(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/items/amulet/{id}")
    @Operation(summary = "Retorna o item amuleto do aventureiro", parameters = @Parameter(required = true, name = "id", example = "1"), responses= @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples=@ExampleObject(value = "\"[\"{\"id\": \"1\", \"type\": \"AMULET\", \"name\": \"amuleto do devorador\", \"extraStrenght\": \"10\", \"extraDefense\": \"10\"}\"]\""))))
    public ResponseEntity<Object> getAmuletInCharacter(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(characterService.getAmuletItemFromCharacter(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
