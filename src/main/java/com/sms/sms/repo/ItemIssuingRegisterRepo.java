package com.sms.sms.repo;

import com.sms.sms.entity.ItemIssuingRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemIssuingRegisterRepo extends JpaRepository<ItemIssuingRegister, Integer> {
    List<ItemIssuingRegister> findByStudentId(String studentId);

}
