package com.panandafog.mt_server.music.repository.last_fm;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.entities.last_fm.LastFmAddTracksOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LastFmAddTracksOperationRepository extends JpaRepository<LastFmAddTracksOperationEntity, Integer> {

    List<LastFmAddTracksOperationEntity> findByIdAndUser(Integer id, AppUser user);
}
