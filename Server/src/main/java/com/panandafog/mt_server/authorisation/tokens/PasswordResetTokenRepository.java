package com.panandafog.mt_server.authorisation.tokens;

import com.panandafog.mt_server.authorisation.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PasswordResetTokenRepository
        extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(AppUser user);

    @Transactional
    @Modifying
    @Query("delete from PasswordResetToken token where token.user.id=:userID")
    Integer deleteAllByUserId(@Param("userID") Integer id);
}
