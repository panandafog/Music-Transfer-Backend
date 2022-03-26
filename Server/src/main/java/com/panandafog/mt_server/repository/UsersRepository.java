package com.panandafog.mt_server.repository;
import com.panandafog.mt_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

}
