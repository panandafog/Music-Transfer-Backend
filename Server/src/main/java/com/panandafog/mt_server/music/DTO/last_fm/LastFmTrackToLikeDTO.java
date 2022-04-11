package com.panandafog.mt_server.music.DTO.last_fm;

import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackToLikeEntity;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastFmTrackToLikeDTO {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Boolean liked;

    @Getter
    @Setter
    private LastFmTrackDTO track;

    public LastFmTrackToLikeEntity entity() {
        return new LastFmTrackToLikeEntity(id, liked, track.entity());
    };
}