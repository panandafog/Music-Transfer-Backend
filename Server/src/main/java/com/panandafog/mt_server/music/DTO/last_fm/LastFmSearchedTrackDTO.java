package com.panandafog.mt_server.music.DTO.last_fm;

import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackEntity;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.*;

import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastFmSearchedTrackDTO {

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
    private Set<LastFmTrackDTO> foundTracks;

    public LastFmSearchedTrackEntity entity() {
        Stream<LastFmTrackEntity> stream = foundTracks.stream().map(LastFmTrackDTO::entity);
        return new LastFmSearchedTrackEntity(id, triedToSearchTracks, trackToSearch.entity(), stream.collect(Collectors.toSet()));
    };
}