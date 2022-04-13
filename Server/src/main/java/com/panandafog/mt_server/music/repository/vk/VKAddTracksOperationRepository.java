package com.panandafog.mt_server.music.repository.vk;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.entities.vk.VKAddTracksOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VKAddTracksOperationRepository extends JpaRepository<VKAddTracksOperationEntity, Integer> {

    List<VKAddTracksOperationEntity> findByIdAndUser(Integer id, AppUser user);
}
