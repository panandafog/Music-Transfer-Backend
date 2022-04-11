package com.panandafog.mt_server.music.DTO.last_fm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchTracksSuboperationEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackEntity;
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
public class LastFmSearchTracksSuboperationDTO {

    private String id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date started;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date completed;

    private Set<LastFmSearchedTrackDTO> searchedTracks;

    public LastFmSearchTracksSuboperationEntity entity() {
        Stream<LastFmSearchedTrackEntity> stream = searchedTracks.stream().map(LastFmSearchedTrackDTO::entity);
        return new LastFmSearchTracksSuboperationEntity(id, started, completed, stream.collect(Collectors.toSet()));
    };
}
