package com.panandafog.mt_server.music.DTO.shared;

import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedTrackDTO {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String spotifyID;

    @Getter
    @Setter
    private String lastFmID;

    @Getter
    @Setter
    private String vkID;

    @Getter
    @Setter
    private String vkOwnerID;

    @Getter
    @Setter
    private List<String> artists;

    @Getter
    @Setter
    private Integer duration;

    public SharedTrackEntity entity() {
        return new SharedTrackEntity(id, title, spotifyID, lastFmID, vkID, vkOwnerID, artists, duration);
    };
}
