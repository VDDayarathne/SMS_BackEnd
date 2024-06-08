package com.sms.sms.service;

import com.sms.sms.dto.AdminDTO;
import com.sms.sms.entity.Admin;
import com.sms.sms.repo.AdminRepo;
import org.modelmapper.ModelMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor

public class AdminService {

    @Autowired
    private final AdminRepo adminRepo;
    @Autowired
    private ModelMapper modelMapper;


    public AdminRepo saveAdmin(AdminDTO adminDTO) {
        adminRepo.save(modelMapper .map(adminRepo, Admin.class));
        return adminRepo;
    }
    public List<AdminDTO> getAllAdmins() {
        List<Admin> userList = adminRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<AdminDTO>>() {}.getType());
    }
    public AdminDTO updateAdmin(AdminDTO adminDTO) {
        adminRepo.save(modelMapper .map(adminDTO, Admin.class));
        return adminDTO;
    }
    public boolean deleteAdmin(AdminDTO adminDTO) {
        adminRepo.delete(modelMapper .map(adminDTO, Admin.class));
        return true;
    }
    public AdminDTO getAdminByAdminID(String AdminId){
        Optional<Admin> admin = adminRepo.getAdminByAdminID(AdminId);
        return modelMapper.map(admin,AdminDTO.class);
    }
    public List<String> getNotifications() {
        return adminRepo.getNotifications();
    }

}
