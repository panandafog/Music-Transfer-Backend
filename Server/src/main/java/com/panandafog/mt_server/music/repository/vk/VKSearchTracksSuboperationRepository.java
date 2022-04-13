package com.panandafog.mt_server.music.repository.vk;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.entities.vk.VKSearchTracksSuboperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VKSearchTracksSuboperationRepository extends JpaRepository<VKSearchTracksSuboperationEntity, Integer> {

}
