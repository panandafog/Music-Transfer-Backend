package com.panandafog.mt_server.music.DTO.last_fm;

import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackEntity;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastFmTrackDTO {

    private Integer id;
    private String mbid;
    private String name;
    private String artist;
    private String url;

    public LastFmTrackEntity entity() {
        return new LastFmTrackEntity(id, mbid, name, artist, url);
    };
}
