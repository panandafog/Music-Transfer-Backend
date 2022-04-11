package com.panandafog.mt_server.music.repository.shared;

import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedTrackRepository extends JpaRepository<SharedTrackEntity, Integer> {

}
