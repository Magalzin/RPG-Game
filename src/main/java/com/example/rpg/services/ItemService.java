package com.example.rpg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rpg.enums.ItemType;
import com.example.rpg.models.ItemModel;
import com.example.rpg.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    

    public List<ItemModel> getAllItems() {
        return itemRepository.findAll();
    }

    public ItemModel newItem(ItemModel item) {
        if (item.getType() == ItemType.WEAPON && item.getExtraDefense() != 0 || item.getExtraStrength() > 10 || item.getExtraStrength() < 0) {
            item.setExtraDefense(0);
            if (item.getExtraStrength() > 10)
                item.setExtraStrength(10);
            if(item.getExtraStrength() <= 0)
                item.setExtraStrength(1);
        }

        if (item.getType() == ItemType.ARMOR && item.getExtraStrength() != 0 || item.getExtraDefense() > 10) {
            item.setExtraStrength(0);
            if (item.getExtraDefense() > 10)
                item.setExtraDefense(10);
            if(item.getExtraDefense() <= 0)
                item.setExtraDefense(1);
        }
        if (item.getType() == ItemType.AMULET && item.getExtraStrength() > 10 || item.getExtraDefense() > 10) {
            if (item.getExtraStrength() > 10)
                item.setExtraStrength(10);
            if (item.getExtraDefense() > 10)
                item.setExtraDefense(10);
            if(item.getExtraStrength() <= 0)
                item.setExtraStrength(1);
            if(item.getExtraDefense() <= 0)
                item.setExtraDefense(1);
        }
        return itemRepository.save(item);
    }

    public ItemModel findItemById(long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

}
