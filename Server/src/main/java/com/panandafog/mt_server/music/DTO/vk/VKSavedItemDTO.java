package com.panandafog.mt_server.music.DTO.vk;

import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackEntity;
import com.panandafog.mt_server.music.entities.vk.VKSavedItemEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VKSavedItemDTO {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String artist;

    @Getter
    @Setter
    private Integer ownerID;

    @Getter
    @Setter
    private Integer duration;

    public VKSavedItemEntity entity() {
        return new VKSavedItemEntity(id, title, artist, ownerID, duration);
    };
}
