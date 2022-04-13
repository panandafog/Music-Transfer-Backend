package com.panandafog.mt_server.music.DTO.vk;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackDTO;
import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackEntity;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import com.panandafog.mt_server.music.entities.vk.VKSavedItemEntity;
import com.panandafog.mt_server.music.entities.vk.VKSearchedTrackEntity;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VKSearchedTrackDTO {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Boolean triedToSearchTracks;

    @Getter
    @Setter
    private SharedTrackDTO trackToSearch;

    @Getter
    @Setter
    private Set<VKSavedItemDTO> foundTracks;

    public VKSearchedTrackEntity entity() {
        Stream<VKSavedItemEntity> stream = foundTracks.stream().map(VKSavedItemDTO::entity);
        return new VKSearchedTrackEntity(id, triedToSearchTracks, trackToSearch.entity(), stream.collect(Collectors.toSet()));
    };
}
