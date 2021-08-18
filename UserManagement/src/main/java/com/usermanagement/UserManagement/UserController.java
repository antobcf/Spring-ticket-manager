package com.usermanagement.UserManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:9001")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping("")
    public List<User> getAllUsers(/*Model model*/) {
        List<User> users = repo.findAll();
        //model.addAttribute("users", users);
        return users;
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        User utente = repo.findByUsername(username);
        return utente;
    }

    @GetMapping("/role/{role}")
    public List<User> getUserByRole(@PathVariable("role") String role) {
        List<User> users = repo.findByRole(role);
        return users;
    }

    @GetMapping("/role/notadmin")
    public List<User> getUSerNotAdmin() {
        List<User> users = repo.findNotAdmin();
        return users;
    }

    //@PostMapping("/login")
    

    @PostMapping("/register")
    public User createAccount(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return repo.save(user);
    }
    
    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        User utente = repo.findByUsername(username);
        if(utente != null) {
            repo.delete(utente);
        }
        
    }
    
    
}

