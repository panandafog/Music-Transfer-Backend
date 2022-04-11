package com.panandafog.mt_server.music.repository.last_fm;

import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastFmTrackRepository extends JpaRepository<LastFmTrackEntity, Integer> {

}
