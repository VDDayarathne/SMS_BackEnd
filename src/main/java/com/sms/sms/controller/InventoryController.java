package com.sms.sms.controller;


import com.sms.sms.entity.Equipment;
import com.sms.sms.entity.Inventory;
import com.sms.sms.repo.EquipmentRepository;
import com.sms.sms.service.InventoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private EquipmentRepository equipmentRepository;

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        try {
            Inventory createdInventory = inventoryService.createInventory(inventory);
            return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Inventory updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        return inventoryService.updateInventory(id, inventory);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }
}
