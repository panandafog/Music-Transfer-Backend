package com.panandafog.mt_server.music.DTO.last_fm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchTracksSuboperationEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackEntity;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastFmSearchTracksSuboperationDTO {

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
    private Set<LastFmSearchedTrackDTO> searchedTracks;

    public LastFmSearchTracksSuboperationEntity entity() {
        Stream<LastFmSearchedTrackEntity> stream = searchedTracks.stream().map(LastFmSearchedTrackDTO::entity);
        return new LastFmSearchTracksSuboperationEntity(id, started, completed, stream.collect(Collectors.toSet()));
    };
}
