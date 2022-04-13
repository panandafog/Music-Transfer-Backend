package com.panandafog.mt_server.music.repository.vk;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.entities.vk.VKTrackToLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VKTrackToLikeRepository extends JpaRepository<VKTrackToLikeEntity, Integer> {

}
