package com.sms.sms.controller;

import com.sms.sms.dto.AdminDTO;
import com.sms.sms.service.AdminService;
import com.sms.sms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/admin")
@CrossOrigin(origins = "http://192.168.56.1:3000")
@AllArgsConstructor

public class adminController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/getAdmins")
    public List<AdminDTO> getAdmin() {
        return adminService.getAllAdmins();
    }

    @PostMapping("/saveAdmin")
    public AdminDTO saveAdmin(@RequestBody AdminDTO adminDTO){
        return (AdminDTO) adminService.saveAdmin(adminDTO);
    }

    @PutMapping("/updateAdmin")
    public AdminDTO updateAdmin(@RequestBody AdminDTO adminDTO){
        return adminService.updateAdmin(adminDTO);
    }

    @DeleteMapping("/deleteAdmin")
    public boolean deleteAdmin(@RequestBody AdminDTO adminDTO){
        return adminService.deleteAdmin(adminDTO);
    }

    @GetMapping("/getUserByUserId/{userID}")
    public AdminDTO getAdminByAdminID(@PathVariable String adminID){
        return adminService.getAdminByAdminID(adminID);
    }

    @GetMapping("/notifications")
    public List<String> getNotifications() {
        return adminService.getNotifications();
    }

}
