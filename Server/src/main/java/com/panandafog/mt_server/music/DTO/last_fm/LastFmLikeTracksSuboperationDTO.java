package com.panandafog.mt_server.music.DTO.last_fm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.entities.last_fm.*;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastFmLikeTracksSuboperationDTO {

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
    private Set<LastFmTrackToLikeDTO> tracksToLike;

    @Getter
    @Setter
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

