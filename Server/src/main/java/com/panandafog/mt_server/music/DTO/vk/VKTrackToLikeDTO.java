package com.panandafog.mt_server.music.DTO.vk;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackToLikeEntity;
import com.panandafog.mt_server.music.entities.vk.VKSavedItemEntity;
import com.panandafog.mt_server.music.entities.vk.VKTrackToLikeEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VKTrackToLikeDTO {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Boolean liked;

    @Getter
    @Setter
    private VKSavedItemDTO savedItem;

    public VKTrackToLikeEntity entity() {
        return new VKTrackToLikeEntity(id, liked, savedItem.entity());
    };
}
