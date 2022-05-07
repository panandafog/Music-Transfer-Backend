package com.panandafog.mt_server.music.repository.shared;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.entities.shared.LibraryRecordEntity;
import com.panandafog.mt_server.music.entities.vk.VKAddTracksOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<LibraryRecordEntity, Integer> {
    List<LibraryRecordEntity> findByUser(AppUser user);
}
