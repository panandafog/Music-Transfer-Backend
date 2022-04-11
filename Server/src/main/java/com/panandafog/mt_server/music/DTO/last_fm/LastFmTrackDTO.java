package com.panandafog.mt_server.music.DTO.last_fm;

import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackEntity;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastFmTrackDTO {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String mbid;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String artist;

    @Getter
    @Setter
    private String url;

    public LastFmTrackEntity entity() {
        return new LastFmTrackEntity(id, mbid, name, artist, url);
    };
}