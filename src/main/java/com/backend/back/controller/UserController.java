package com.backend.back.controller;


import com.backend.back.dto.ForgotPasswordDTO;
import com.backend.back.dto.LoginDTO;
import com.backend.back.dto.UserDTO;
import com.backend.back.model.Role;
import com.backend.back.model.User;
import com.backend.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // En un entorno real, deberías encriptar la contraseña.
        user.setRole(Role.valueOf(userDTO.getRole().name()));
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO) {
        User loggedInUser = userService.login(loginDTO.getEmail(), loginDTO.getPassword());
        return ResponseEntity.ok(loggedInUser);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordDTO dto) {
        String password = userService.getPasswordByEmail(dto.getEmail());
        if (password != null) {
            return ResponseEntity.ok(" Contraseña : " + password);
        } else {
            return ResponseEntity.status(404).body("Correo no encontrado.");
        }
    }
}