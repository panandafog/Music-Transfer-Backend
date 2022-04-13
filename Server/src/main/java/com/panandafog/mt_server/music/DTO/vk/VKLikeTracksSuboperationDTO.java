package com.panandafog.mt_server.music.DTO.vk;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackToLikeDTO;
import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmLikeTracksSuboperationEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackToLikeEntity;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import com.panandafog.mt_server.music.entities.vk.VKLikeTracksSuboperationEntity;
import com.panandafog.mt_server.music.entities.vk.VKTrackToLikeEntity;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VKLikeTracksSuboperationDTO {

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
    private Set<VKTrackToLikeDTO> tracksToLike;

    @Getter
    @Setter
    private Set<SharedTrackDTO> notFoundTracks;

    @Getter
    @Setter
    private Set<SharedTrackDTO> duplicates;

    public VKLikeTracksSuboperationEntity entity() {
        Stream<VKTrackToLikeEntity> tracksToLikeStream = tracksToLike.stream().map(VKTrackToLikeDTO::entity);
        Stream<SharedTrackEntity> notFoundTracksStream = notFoundTracks.stream().map(SharedTrackDTO::entity);
        Stream<SharedTrackEntity> duplicatedTracksStream = duplicates.stream().map(SharedTrackDTO::entity);
        return new VKLikeTracksSuboperationEntity(
                id,
                started,
                completed,
                tracksToLikeStream.collect(Collectors.toSet()),
                notFoundTracksStream.collect(Collectors.toSet()),
                duplicatedTracksStream.collect(Collectors.toSet())
        );
    };
}
