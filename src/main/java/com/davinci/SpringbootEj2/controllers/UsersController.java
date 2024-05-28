package com.davinci.SpringbootEj2.controllers;

import com.davinci.SpringbootEj2.models.DTO.UserDTO;
import com.davinci.SpringbootEj2.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping("/users")
public class UsersController {
    ArrayList<User> users = new ArrayList<>();


    @GetMapping("/user/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
        if (users.isEmpty()){
            //return "No hay usuarios registrados aun";
            return new ResponseEntity<>(new UserDTO(), HttpStatus.BAD_REQUEST);
        }
        Optional<User> usuarioEncontrado = users.stream().filter(usuario ->
                usuario.getEmail().equalsIgnoreCase(email)).findFirst();
        if(usuarioEncontrado.isPresent()){
            return new ResponseEntity<>(new UserDTO(usuarioEncontrado.get()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new UserDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{name}/{email}/{age}/{password}")
    public ResponseEntity<UserDTO> registerUserByPath (@PathVariable String name, @PathVariable String email, @PathVariable String age, @PathVariable String password){

        return new ResponseEntity<>(new UserDTO(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> registerUserByQuery(@RequestParam String name, @RequestParam String email, @RequestParam String age, @RequestParam String password){
        String validationResponseMessage = validateUserRegistration(name, email, age, password);
        if(validationResponseMessage.isBlank()){
            User usuarioNuevo = new User(name, email, Integer.parseInt(age), password);
            users.add(usuarioNuevo);
            return new ResponseEntity<>(new UserDTO(usuarioNuevo), HttpStatus.OK);
            // return "El usuario fue agregado correctamente";
        }
        // return validationResponseMessage;
        return new ResponseEntity<>(new UserDTO(), HttpStatus.BAD_REQUEST);

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

        String validationResponseMessage = validateUserRegistration(name, email, age, password);
        if(validationResponseMessage.isBlank()){
            User usuarioNuevo = new User(name, email, Integer.parseInt(age), password);
            users.add(usuarioNuevo);
            return new ResponseEntity<>(new UserDTO(usuarioNuevo), HttpStatus.OK);
            //return "El usuario fue agregado correctamente";
        }
        return new ResponseEntity<>(new UserDTO(), HttpStatus.BAD_REQUEST);
        //return validationResponseMessage;
    }





















}
