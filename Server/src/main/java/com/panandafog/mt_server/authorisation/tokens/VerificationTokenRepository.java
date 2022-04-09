package com.panandafog.mt_server.authorisation.tokens;

import com.panandafog.mt_server.authorisation.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(AppUser user);

    @Transactional
    @Modifying
    @Query("delete from VerificationToken token where token.user.id=:userID")
    Integer deleteAllByUserId(@Param("userID") Integer id);
}
