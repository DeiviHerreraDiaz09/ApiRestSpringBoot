package com.mangaproject.manga_asd.Controller;

import com.mangaproject.manga_asd.Model.User;
import com.mangaproject.manga_asd.Service.IUserService;
import com.mangaproject.manga_asd.Service.JwtVerificationService;
import com.mangaproject.manga_asd.Utils.JwtUtil;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtVerificationService jwtVerificationService;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        log.info("Añadiendo un nuevo usuario:" + user.getEmail());
        return userService.save(user);
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        log.info("Obteniendo todos los usuarios");
        return userService.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User authenticatedUser = userService.authenticate(user.getEmail(), user.getPassword());

        if (authenticatedUser != null) {
            Integer userId = authenticatedUser.getIdUsuario();
            String role = authenticatedUser.getRole();

            ArrayList<Object> token = new ArrayList<>();

            log.info("Usuario autenticado con ID: " + userId);

            final String adminToken = jwtUtil.generateToken(userId, "adminUsername", authenticatedUser.getRole());

            token.add(userId);
            token.add(adminToken);
            token.add(role);

            Map<String, String> response = new HashMap<>();
            response.put("token", adminToken);

            return ResponseEntity.ok(token);
        } else {
            log.warning("Autenticación fallida para el usuario: " + user.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("error", "Autenticación fallida");

            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/verificar")
    public ResponseEntity<?> verifyToken(@RequestHeader(name = "Authorization") String jwtToken) {
        return jwtVerificationService.verifyToken(jwtToken);
    }
}
