//package com.panandafog.mt_server.repository;
//import com.panandafog.mt_server.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//public interface UsersRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.email = ?1")
//    public User findByEmail(String email);
//}
