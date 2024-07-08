package com.sms.sms.repo;

import com.sms.sms.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByName(String name);
}
