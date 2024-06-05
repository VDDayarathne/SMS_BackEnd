package com.sms.sms.service;

import com.sms.sms.dto.UserDTO;
import com.sms.sms.entity.User;
import com.sms.sms.repo.UserRepo;
import com.sms.sms.util.VarList;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor

public class UserService{



    @Autowired
    private final UserRepo userRepo;

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User getUser(String email, String password) {
        Optional<User> userOptional = userRepo.findUserByEmail(email);
        if (userOptional.isPresent()) {
            if (!userOptional.get().getPassword().equals(password)) {
                throw new IllegalStateException("password is not correct for email: "+ email);
            }
        }else {
            throw new IllegalStateException("email: " + email + " is not present");
        }
        return userOptional.get();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepo
                .findUserByEmail(user.getEmail());
        if(userOptional.isPresent()) {
            throw new IllegalStateException("email already taken");
        }
        userRepo.save(user);
    }

    public void deleteUserByEmail(String email) {
        Optional<User> userOptional = userRepo
                .findUserByEmail(email);
        if(userOptional.isEmpty()) {
            throw new IllegalStateException("user with email: " + email + " doesn't exist");
        }
        userRepo.deleteById(userOptional.get().getId());
    }
    /*@Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    public String saveUser(UserDTO userDTO){
        if (userRepo.existsById(userDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else {
            userRepo.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateUser(UserDTO userDTO){
        if (userRepo.existsById(userDTO.getId())){
            userRepo.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<UserDTO> getAllUser(){
    List<User> usersList = userRepo.findAll();
    return modelMapper.map(usersList, new TypeToken<ArrayList<UserDTO>>(){}.getType());
    }

    public UserDTO searchUser(int id){
    if (userRepo.existsById(id)){
        User user = userRepo.findById(id).orElse(null);
        return modelMapper.map(user, UserDTO.class);
    }
    else {
        return null;
    }
    }

    public String deleteUser(int id){
        if (userRepo.existsById(id)){
            userRepo.deleteById(id);
            return VarList.RSP_SUCCESS;
        }
        else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }*/


    /*public UserDTO saveUser(UserDTO userDTO) {
        userRepo.save(modelMapper .map(userDTO, User.class));
        return userDTO;
    }
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>() {}.getType());
    }
    public UserDTO updateUser(UserDTO userDTO) {
        userRepo.save(modelMapper .map(userDTO, User.class));
        return userDTO;
    }
    public boolean deleteUser(UserDTO userDTO) {
        userRepo.delete(modelMapper .map(userDTO, User.class));
        return true;
    }
    public UserDTO getUserByUserID(String UserId){
        User user = userRepo.getUserByUserID(UserId);
        return modelMapper.map(user,UserDTO.class);
    }
    public UserDTO getUserByUserIDAndFaculty(String userId, String faculty){
        User user = userRepo.getUserByUserIDAndFaculty(userId,faculty);
        return modelMapper.map(user,UserDTO.class);
    }*/



}