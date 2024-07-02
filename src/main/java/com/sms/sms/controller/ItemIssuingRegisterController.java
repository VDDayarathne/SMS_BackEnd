package com.sms.sms.controller;

import com.sms.sms.entity.ItemIssuingRegister;
import com.sms.sms.service.ItemIssuingRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class ItemIssuingRegisterController {
    @Autowired
    private ItemIssuingRegisterService itemIssuingRegisterService;

    @PostMapping("/create-item-issuing-register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ItemIssuingRegister createItemIssuingRegister(@RequestBody ItemIssuingRegister itemIssuingRegister) {
        return itemIssuingRegisterService.createItemIssuingRegister(itemIssuingRegister);
    }

    @GetMapping("/get-all-item-issuing-registers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ItemIssuingRegister> getAllItemIssuingRegisters() {
        return itemIssuingRegisterService.getAllItemIssuingRegisters();
    }

    @GetMapping("/get-item-issuing-register/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ItemIssuingRegister getItemIssuingRegisterById(@PathVariable Integer id) {
        return itemIssuingRegisterService.getItemIssuingRegisterById(id);
    }

    @PutMapping("/update-item-issuing-register/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ItemIssuingRegister updateItemIssuingRegister(@PathVariable Integer id, @RequestBody ItemIssuingRegister itemIssuingRegister) {
        return itemIssuingRegisterService.updateItemIssuingRegister(id, itemIssuingRegister);
    }

    @DeleteMapping("/delete-item-issuing-register/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteItemIssuingRegister(@PathVariable Integer id) {
        itemIssuingRegisterService.deleteItemIssuingRegister(id);
    }
}