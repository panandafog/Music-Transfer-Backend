package com.panandafog.mt_server.repository;

import com.panandafog.mt_server.entity.AppUser;
import com.panandafog.mt_server.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository
        extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(AppUser user);
}
