package com.panandafog.mt_server.music.DTO.last_fm;

import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackToLikeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastFmTrackToLikeDTO {

    private Integer id;
    private Boolean liked;
    private LastFmTrackDTO track;

    public LastFmTrackToLikeEntity entity() {
        return new LastFmTrackToLikeEntity(id, liked, track.entity());
    };
}