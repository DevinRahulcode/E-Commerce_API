package com.example.ECom.controller;


import com.example.ECom.exception.RecordNotFoundException;
import com.example.ECom.model.User;
import com.example.ECom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record Not Found"+id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User updateUserDetails){
        User updateUser = userRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Record Not found"+id));

        updateUser.setName(updateUserDetails.getName());
        updateUser.setEmail(updateUserDetails.getEmail());
        updateUser.setAddress(updateUserDetails.getAddress());
        updateUser.setPhone(updateUserDetails.getPhone());


        userRepository.save(updateUser);

        return ResponseEntity.ok(updateUser);


    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
        User user = userRepository.findById(id).orElseThrow(()->new RecordNotFoundException("Record Not Found"+id));

        userRepository.delete(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
