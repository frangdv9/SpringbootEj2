package com.davinci.SpringbootEj2.services;

import com.davinci.SpringbootEj2.models.DTO.UserDTO;
import com.davinci.SpringbootEj2.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class UsersService {

    private List<User> users = new ArrayList<>();

    public User registrarUsuario(String name, String email, String age, String password ){
        String validationResponseMessage = validateUserRegistration(name, email, age, password);
        if(validationResponseMessage.isBlank()){
            User usuarioNuevo = new User(name, email, Integer.parseInt(age), password);
            users.add(usuarioNuevo);
            return usuarioNuevo;
        }
        return null;
    }
    private String validateUserRegistration(String name, String email, String age, String password){
        if (name.isBlank() || email.isBlank() || age.isBlank() || password.isBlank() ){
            return "Por favor verifique que no esté enviando ningun valor vacío";
        }
        Integer parsedAge = 0;
        try {
            parsedAge = Integer.parseInt(age);
        }catch (NumberFormatException e){
            return "La edad debe ser un valor numerico";
        }
        if (password.length() < 8 || password.length() > 16){
            return "La contraseña debe contener entre 8 y 16 caracteres";
        }
        if (parsedAge < 18 ){
            return "El usuario debe tener como minimo 18 años de edad";
        }
        if (!users.isEmpty()){
            if (users.stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst().isPresent()){
                return "Ya existe un usuario registrado con este mail";
            }
        }

        return "";
    }
}
