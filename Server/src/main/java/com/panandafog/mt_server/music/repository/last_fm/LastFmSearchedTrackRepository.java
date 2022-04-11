package com.panandafog.mt_server.music.repository.last_fm;

import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastFmSearchedTrackRepository extends JpaRepository<LastFmSearchedTrackEntity, Integer> {

}
