package com.sms.sms.controller;


import com.sms.sms.entity.Inventory;
import com.sms.sms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/sport-categories/{sportCategoryId}")
    public List<Inventory> getInventoriesBySportCategory(@PathVariable Long sportCategoryId) {
        return inventoryService.getInventoriesBySportCategory(sportCategoryId);
    }

    @GetMapping("/equipments/{equipmentId}")
    public List<Inventory> getInventoriesByEquipment(@PathVariable Long equipmentId) {
        return inventoryService.getInventoriesByEquipment(equipmentId);
    }

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryService.createInventory(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        return inventoryService.updateInventory(id, inventory);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }



}
