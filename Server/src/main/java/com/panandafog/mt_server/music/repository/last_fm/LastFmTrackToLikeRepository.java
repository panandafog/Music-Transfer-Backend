package com.panandafog.mt_server.music.repository.last_fm;

import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackToLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastFmTrackToLikeRepository extends JpaRepository<LastFmTrackToLikeEntity, Integer> {

}
