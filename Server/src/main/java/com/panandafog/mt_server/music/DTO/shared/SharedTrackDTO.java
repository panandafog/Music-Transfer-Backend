package com.panandafog.mt_server.music.DTO.shared;

import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedTrackDTO {

    private Integer id;
    private String title;
    private String spotifyID;
    private String lastFmID;
    private String vkID;
    private String vkOwnerID;
    private List<String> artists;
    private Integer duration;

    public SharedTrackEntity entity() {
        return new SharedTrackEntity(id, title, spotifyID, lastFmID, vkID, vkOwnerID, artists, duration);
    };
}
