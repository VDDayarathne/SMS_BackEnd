package com.sms.sms.service;


import com.sms.sms.entity.Equipment;
import com.sms.sms.entity.Inventory;
import com.sms.sms.entity.SportCategory;
import com.sms.sms.repo.EquipmentRepository;
import com.sms.sms.repo.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElseThrow();
    }

    public Inventory createInventory(Inventory inventory) {
        Equipment equipment = inventory.getEquipment();
        if (equipment!= null) {
            Optional<Equipment> optionalEquipment = equipmentRepository.findByName(equipment.getName());
            if (optionalEquipment.isPresent()) {
                inventory.setEquipment(optionalEquipment.get());
            } else {
                equipment = equipmentRepository.save(equipment);
                inventory.setEquipment(equipment);
            }
            inventory.setUpdatedAt(new Date());
            return inventoryRepository.save(inventory);
        } else {
            throw new IllegalArgumentException("Invalid Equipment object. Equipment cannot be null.");
        }
    }

    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existingInventory = getInventoryById(id);
        existingInventory.setDescription(inventory.getDescription());
        existingInventory.setQuantity(inventory.getQuantity());
        existingInventory.setSportCategory(inventory.getSportCategory());
        existingInventory.setEquipment(inventory.getEquipment());
        existingInventory.setUpdatedAt(new Date());
        return inventoryRepository.save(existingInventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}