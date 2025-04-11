package com.example.rpg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rpg.models.ItemModel;
import com.example.rpg.services.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemservice;

    @GetMapping
    @Operation(summary = "Retorna os itens", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "[{\"id\": \"1\", \"type\": \"AMULET\", \"name\": \"amuleto do devorador\", \"extraStrenght\": \"10\", \"extraDefense\": \"10\"},]"))))
    public ResponseEntity<Object> getAllItems() {
        try {
            return ResponseEntity.ok(itemservice.getAllItems());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Cria um item", requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Exemplo de Item", summary = "Item do joaquim", value = "{\"type\": \"AMULET\", \"name\": \"amuleto do devorador\", \"extraStrenght\": \"10\", \"extraDefense\": \"10\"}"))), responses= @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples=@ExampleObject(value = "[{\"id\": \"1\", \"type\": \"AMULET\", \"name\": \"amuleto do devorador\", \"extraStrenght\": \"10\", \"extraDefense\": \"10\"},]"))))
    public ResponseEntity<Object> getAllItems(@RequestBody ItemModel item) {
        try {
            return ResponseEntity.ok(itemservice.newItem(item));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna o item pelo identificador", parameters = @Parameter(required = true, name = "id", example = "1"), responses= @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples=@ExampleObject(value = "[{\"id\": \"1\", \"type\": \"AMULET\", \"name\": \"amuleto do devorador\", \"extraStrenght\": \"10\", \"extraDefense\": \"10\"},]"))))
    public ResponseEntity<Object> getItemById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(itemservice.findItemById(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
