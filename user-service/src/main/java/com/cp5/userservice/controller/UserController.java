package com.cp5.userservice.controller;

import com.cp5.userservice.model.User;
import com.cp5.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // 🔥 Criar usuário
    @PostMapping
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    // 🔍 Listar todos
    @GetMapping
    public List<User> getAll() {
        return service.findAll();
    }

    // 🔎 Buscar por ID
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.findById(id);
    }

    // 🔄 Atualizar usuário
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return service.update(id, user);
    }

    // ❌ Desativar usuário
    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }

    // 🔐 Login simples
    @PostMapping("/login")
    public User login(@RequestParam String email,
                      @RequestParam String senha) {
        return service.login(email, senha);
    }
}