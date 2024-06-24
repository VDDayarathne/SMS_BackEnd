package com.sms.sms.repo;

import com.sms.sms.entity.Equipment;
import com.sms.sms.entity.Inventory;
import com.sms.sms.entity.SportCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySportCategory(SportCategory sportCategory);
    List<Inventory> findByEquipment(Equipment equipment);
}
