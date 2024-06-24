package com.sms.sms.service;


import com.sms.sms.entity.Equipment;
import com.sms.sms.entity.Inventory;
import com.sms.sms.entity.SportCategory;
import com.sms.sms.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getInventoriesBySportCategory(Long sportCategoryId) {
        SportCategory sportCategory = new SportCategory();
        sportCategory.setId(sportCategoryId);
        return inventoryRepository.findBySportCategory(sportCategory);
    }

    public List<Inventory> getInventoriesByEquipment(Long equipmentId) {
        Equipment equipment = new Equipment();
        equipment.setId(equipmentId);
        return inventoryRepository.findByEquipment(equipment);
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existingInventory = inventoryRepository.findById(id).orElseThrow();
        existingInventory.setName(inventory.getName());
        existingInventory.setDescription(inventory.getDescription());
        existingInventory.setQuantity(inventory.getQuantity());
        existingInventory.setSportCategory(inventory.getSportCategory());
        existingInventory.setEquipment(inventory.getEquipment());
        return inventoryRepository.save(existingInventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

}
