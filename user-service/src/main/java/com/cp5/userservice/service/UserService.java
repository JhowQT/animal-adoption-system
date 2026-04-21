package com.cp5.userservice.service;

import com.cp5.userservice.model.User;
import com.cp5.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // 🔥 Criar usuário com validação completa
    public User create(User user) {

        // valida idade
        if (user.getAge() == null || user.getAge() < 18) {
            throw new RuntimeException("Usuário deve ser maior de idade");
        }

        // valida email duplicado
        repository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Email já cadastrado");
        });

        // valida senha
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new RuntimeException("Senha é obrigatória");
        }

        // ativa usuário automaticamente
        user.setActive(true);

        return repository.save(user);
    }

    // 🔍 Listar todos
    public List<User> findAll() {
        return repository.findAll();
    }

    // 🔎 Buscar por ID
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // 🔄 Atualizar dados
    public User update(Long id, User updatedUser) {

        User user = findById(id);

        user.setName(updatedUser.getName());
        user.setCity(updatedUser.getCity());
        user.setHasYard(updatedUser.getHasYard());
        user.setHomeType(updatedUser.getHomeType());
        user.setExperienceLevel(updatedUser.getExperienceLevel());
        user.setPreferredAnimalSize(updatedUser.getPreferredAnimalSize());

        return repository.save(user);
    }

    // ❌ Desativar usuário (soft delete)
    public void deactivate(Long id) {
        User user = findById(id);
        user.setActive(false);
        repository.save(user);
    }

    // 🧠 Validação para adoção (importante pro futuro)
    public boolean isEligibleForAdoption(User user) {
        return user.getActive() != null && user.getActive()
                && user.getAge() >= 18;
    }

    // 🔐 Validação simples de login (básico)
    public User login(String email, String senha) {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getPassword().equals(senha)) {
            throw new RuntimeException("Senha inválida");
        }

        return user;
    }
}
//teste