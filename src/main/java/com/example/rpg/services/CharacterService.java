package com.example.rpg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rpg.enums.ItemType;
import com.example.rpg.models.CharacterModel;
import com.example.rpg.models.ItemModel;
import com.example.rpg.repository.CharacterRepository;

@Service
public class CharacterService {
    @Autowired()
    private CharacterRepository characterRepository;

    @Autowired
    private ItemService itemService;

    public CharacterModel createCharacter(CharacterModel character) {
        if ((character.getStrength() + character.getDefense()) > 10) {
            throw new RuntimeException("Os aventureiro não pode ter os status somando mais de 10");
        }
        return characterRepository.save(character);
    }

    public List<CharacterModel> getAllCharacters() {
        return characterRepository.findAll();
    }

    public CharacterModel getCharacterById(Long id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar o aventureiro"));
    }

    public CharacterModel updateAdventurerNameById(Long id, String newAdventurerName) {
        CharacterModel model = getCharacterById(id);
        model.setAdventurer_name(newAdventurerName);
        return characterRepository.save(model);
    }

    public void deleteCharacter(Long id) {
        CharacterModel model = getCharacterById(id);
        try {
            characterRepository.delete(model);
        } catch (Exception e) {
            throw new RuntimeException("Character não aventureiro ou já foi deletado!!");
        }
    }

    public CharacterModel addItemInCharacter(Long idItem, Long idUser) {
        CharacterModel model = getCharacterById(idUser);
        if(model == null){
            throw new RuntimeException("Usuário não encontrado para inserção de item");
        }
        ItemModel item = itemService.findItemById(idItem);
        if(item == null){
            throw new RuntimeException("Item não encontrado para inserção");

        }
        if (!model.getMagicItems().isEmpty()) {
            for (ItemModel i : model.getMagicItems()) {
                if (i.getType() == ItemType.AMULET) {
                    throw new RuntimeException("O aventureiro já possui um amuleto, remova-o para adicionar mais");
                }
                if (i.getName().equals(item.getName())) {
                    throw new RuntimeException("O aventureiro já possui esse item");
                }
            }
        }
        try {
            model.addMagicItem(item);
            model.setStrength(model.getStrength() + item.getExtraStrength());
            model.setDefense(model.getDefense() + item.getExtraStrength());
            return characterRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel adicionar o item");
        }
    }

    public CharacterModel removeItemFromCharacter(Long idItem, Long idUser) {
        CharacterModel model = getCharacterById(idUser);
        ItemModel item = itemService.findItemById(idItem);
        try {
            model.addMagicItem(item);
            model.setStrength(model.getStrength() - item.getExtraStrength());
            model.setDefense(model.getDefense() - item.getExtraStrength());
            return characterRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel remover o item");
        }
    }

    public List<ItemModel> getAllItemsFromCharacter(Long id) {
        CharacterModel model = getCharacterById(id);

        try {
            return model.getMagicItems();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao pegar os items do usuário");
        }
    }

    public ItemModel getAmuletItemFromCharacter(Long id) {
        CharacterModel model = getCharacterById(id);

        try {
            ItemModel itemModel = null;
            for (ItemModel i : model.getMagicItems()) {
                if (i.getType() == ItemType.AMULET) {
                    itemModel = i;
                }
            }
            return itemModel;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao pegar os items do usuário");
        }
    }

}
