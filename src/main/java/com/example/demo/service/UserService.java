package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
@Transactional

public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    public List<UserDTO> getAllUsers() {
        List<User>userList = userRepo.findAll();
        return modelMapper.map(userList,new TypeToken<List<UserDTO>>(){}.getType());
    }

    public UserDTO saveUser(UserDTO userDTO){
        userRepo.save(modelMapper.map( userDTO , User.class)); //save kiynne default method ekk. UserDTO eka User.class type ekt mapp krgannawa
        return userDTO;
    }

    public UserDTO updateUser(UserDTO userDTO){
        userRepo.save(modelMapper.map( userDTO , User.class));
        return userDTO;
    }

    public String deleteUser(Integer userId){
       // userRepo.delete(modelMapper.map( userDTO , User.class));
        userRepo.deleteById(userId);
        return "User Deleted";
    }

    public UserDTO getUserById(Integer userId){
        User user = userRepo.getUserById(userId);
        return modelMapper.map(user,UserDTO.class);
    }
}
