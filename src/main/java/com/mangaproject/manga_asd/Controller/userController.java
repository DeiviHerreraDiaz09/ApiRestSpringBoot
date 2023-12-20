package com.mangaproject.manga_asd.Controller;


import com.mangaproject.manga_asd.Model.User;
import com.mangaproject.manga_asd.Service.IUserService;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Log
@RestController
@RequestMapping("/api/users")
public class userController {


    @Autowired
    private IUserService userd;

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

    

}
