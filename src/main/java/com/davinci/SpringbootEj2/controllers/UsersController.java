package com.davinci.SpringbootEj2.controllers;

import com.davinci.SpringbootEj2.models.DTO.UserDTO;
import com.davinci.SpringbootEj2.models.User;
import com.davinci.SpringbootEj2.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;


    @GetMapping("/user/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
        User usuarioEncontrado = usersService.encontrarUsuario(email);
        if (usuarioEncontrado!=null) {
            return new ResponseEntity<>(new UserDTO(usuarioEncontrado), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{name}/{email}/{age}/{password}")
    public ResponseEntity<UserDTO> registerUserByPath (@PathVariable String name, @PathVariable String email, @PathVariable String age, @PathVariable String password){
        User usuarioCreado = usersService.registrarUsuario(name, email, age, password);
        if (usuarioCreado!=null) {
            return new ResponseEntity<>(new UserDTO(usuarioCreado), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> registerUserByQuery(@RequestParam String name, @RequestParam String email, @RequestParam String age, @RequestParam String password){
        User usuarioCreado = usersService.registrarUsuario(name, email, age, password);
        if (usuarioCreado!=null) {
            return new ResponseEntity<>(new UserDTO(usuarioCreado), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/user")
    public ResponseEntity<UserDTO> registerUser(@RequestBody Map<String, Object> requestBody) {
        String name;
        String email;
        String age;
        String password;
        try {
            name = requestBody.get("name").toString();
            email = requestBody.get("email").toString();
            age = requestBody.get("age").toString();
            password = requestBody.get("password").toString();
        }catch (Exception e){
            return new ResponseEntity<>(new UserDTO(), HttpStatus.BAD_REQUEST);
            //return "No han sido recibido los datos en su totalidad";
        }
        User usuarioCreado = usersService.registrarUsuario(name, email, age, password);
        if (usuarioCreado!=null) {
            return new ResponseEntity<>(new UserDTO(usuarioCreado), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }





















}
