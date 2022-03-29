package com.panandafog.mt_server;

import static org.assertj.core.api.Assertions.assertThat;

import com.panandafog.mt_server.entity.User;
import com.panandafog.mt_server.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class UsersRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsersRepository repository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("testuser1@gmail.com");
        user.setPassword("testpassword1");
        user.setFirstName("testusername1");
        user.setLastName("testuserlastname1");

        User savedUser = repository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }
}

