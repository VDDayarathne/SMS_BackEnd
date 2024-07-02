package com.sms.sms.service;


import com.sms.sms.entity.ItemIssuingRegister;
import com.sms.sms.repo.ItemIssuingRegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemIssuingRegisterService {
    @Autowired
    private ItemIssuingRegisterRepo itemIssuingRegisterRepo;

    public ItemIssuingRegister createItemIssuingRegister(ItemIssuingRegister itemIssuingRegister) {
        return itemIssuingRegisterRepo.save(itemIssuingRegister);
    }

    public List<ItemIssuingRegister> getAllItemIssuingRegisters() {
        return itemIssuingRegisterRepo.findAll();
    }

    public ItemIssuingRegister getItemIssuingRegisterById(Integer id) {
        return itemIssuingRegisterRepo.findById(id).orElseThrow();
    }

    public ItemIssuingRegister updateItemIssuingRegister(Integer id, ItemIssuingRegister itemIssuingRegister) {
        ItemIssuingRegister existingItemIssuingRegister = getItemIssuingRegisterById(id);
        existingItemIssuingRegister.setIssuingTime(itemIssuingRegister.getIssuingTime());
        existingItemIssuingRegister.setReturningTime(itemIssuingRegister.getReturningTime());
        existingItemIssuingRegister.setItems(itemIssuingRegister.getItems());
        existingItemIssuingRegister.setDescription(itemIssuingRegister.getDescription());
        existingItemIssuingRegister.setStudentId(itemIssuingRegister.getStudentId());
        return itemIssuingRegisterRepo.save(existingItemIssuingRegister);
    }

    public void deleteItemIssuingRegister(Integer id) {
        itemIssuingRegisterRepo.deleteById(id);
    }
}