package com.panandafog.mt_server.repository;

import com.panandafog.mt_server.entity.AppUser;
import com.panandafog.mt_server.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(AppUser user);
}
