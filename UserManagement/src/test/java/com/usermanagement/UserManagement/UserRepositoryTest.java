package com.usermanagement.UserManagement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreteUser() {
        User user = new User();
        user.setUsername("paolo");
        user.setPassword("password");
        user.setFirstname("paolo");
        user.setLastname("rossi");
        user.setEmail("paolo@gmail.com");
        user.setRole("employee");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getUsername());

        assertThat(existUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void testFindUserByUsername() {
        String username = "paolo";

        User user = repo.findByUsername(username);

        assertThat(user).isNotNull();
    }
}
