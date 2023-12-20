package com.mangaproject.manga_asd.Controller;


import com.mangaproject.Utils.JwtUtil;
import com.mangaproject.manga_asd.Model.User;
import com.mangaproject.manga_asd.Service.IUserService;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
@RestController
@RequestMapping("/api/users")
public class userController {


    @Autowired
    private IUserService userd;

    @Autowired
    private JwtUtil jwtUtil;

    // 1. USUARIOS PUEDAN REGISTRARSE EN LA PLATAFORMA
     
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        
        log.info("Añadiendo un nuevo usuario:" + user.getEmail());
        
        return userd.save(user);
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        log.info("Obteniendo todos los usuarios");
        return userd.findAll();
    }

     // 2. IMPLEMENTACIÓN DE LOGIN CON JWT

     @PostMapping("/login")
     public ResponseEntity<?> loginUser(@RequestBody User user) {
        
         User authenticatedUser = userd.authenticate(user.getEmail(), user.getPassword());
     
         if (authenticatedUser != null) {
             final String jwt = jwtUtil.generateToken(authenticatedUser);
     
             Map<String, String> response = new HashMap<>();
             response.put("token", jwt);
     
             return ResponseEntity.ok(response);
         } else {
             Map<String, String> response = new HashMap<>();
             response.put("error", "Autenticación fallida");
             return ResponseEntity.badRequest().body(response);
         }
     }
     
    

}
