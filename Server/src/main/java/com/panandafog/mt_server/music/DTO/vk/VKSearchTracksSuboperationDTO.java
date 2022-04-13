package com.panandafog.mt_server.music.DTO.vk;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmSearchedTrackDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchTracksSuboperationEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import com.panandafog.mt_server.music.entities.vk.VKSearchTracksSuboperationEntity;
import com.panandafog.mt_server.music.entities.vk.VKSearchedTrackEntity;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VKSearchTracksSuboperationDTO {

    @Getter
    @Setter
    private Integer id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Getter
    @Setter
    private Date started;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Getter
    @Setter
    private Date completed;

    @Getter
    @Setter
    private Set<VKSearchedTrackDTO> searchedTracks;

    public VKSearchTracksSuboperationEntity entity() {
        Stream<VKSearchedTrackEntity> stream = searchedTracks.stream().map(VKSearchedTrackDTO::entity);
        return new VKSearchTracksSuboperationEntity(id, started, completed, stream.collect(Collectors.toSet()));
    };
}
