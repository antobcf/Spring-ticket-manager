package com.usermanagement.UserManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    // @Autowired
    // private JwtUtil jwtUtil;

    // @Autowired
    // private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public List<User> getAllUsers() {
        List<User> users = repo.findAll();
        return users;
    }

    // @PostMapping("/authenticate")
    // public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        
    //     try {
    //         authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
    //         );
    //     } catch(Exception ex) {
    //         throw new Exception("invalid username or password");
    //     }

    //     return jwtUtil.generateToken(authRequest.getUsername());

    // }

    @CrossOrigin(origins = "http://localhost:9001")
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

    @PostMapping("/register")
    public void createAccount(@RequestBody User user) {
        User existingUser = repo.findByUsername(user.getUsername());
        if(existingUser == null) {
            // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            // String encodedPassword = encoder.encode(user.getPassword());
            // user.setPassword(encodedPassword);
            repo.save(user);
        }
    }
    
    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        User utente = repo.findByUsername(username);
        if(utente != null) {
            repo.delete(utente);
        }
    }
    
}

