package com.panandafog.mt_server.music.DTO.last_fm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.entities.last_fm.*;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastFmLikeTracksSuboperationDTO {

    private String id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date started;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date completed;

    private Set<LastFmTrackToLikeDTO> tracksToLike;
    private Set<SharedTrackDTO> notFoundTracks;

    public LastFmLikeTracksSuboperationEntity entity() {
        Stream<LastFmTrackToLikeEntity> tracksToLikeStream = tracksToLike.stream().map(LastFmTrackToLikeDTO::entity);
        Stream<SharedTrackEntity> notFoundTracksStream = notFoundTracks.stream().map(SharedTrackDTO::entity);
        return new LastFmLikeTracksSuboperationEntity(
                id,
                started,
                completed,
                tracksToLikeStream.collect(Collectors.toSet()),
                notFoundTracksStream.collect(Collectors.toSet())
        );
    };
}

