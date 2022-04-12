package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmSearchTracksSuboperationDTO;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmSearchedTrackDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "last_fm_search_tracks_suboperations")
@RequiredArgsConstructor
public class LastFmSearchTracksSuboperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Date started;

    @Getter
    @Setter
    private Date completed;

    @OneToMany(mappedBy="searchTracksSuboperation", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Set<LastFmSearchedTrackEntity> searchedTracks;

    @OneToOne(mappedBy = "searchSuboperation", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private LastFmAddTracksOperationEntity addTracksOperation;

    public LastFmSearchTracksSuboperationEntity(Integer id, Date started, Date completed, Set<LastFmSearchedTrackEntity> searchedTracks) {
        this.id = id;
        this.started = started;
        this.completed = completed;
        this.searchedTracks = searchedTracks;

        this.searchedTracks.forEach(t -> t.setSearchTracksSuboperation(this));
    }

    public LastFmSearchTracksSuboperationDTO dto() {
        Stream<LastFmSearchedTrackDTO> stream = searchedTracks.stream().map(LastFmSearchedTrackEntity::dto);
        return new LastFmSearchTracksSuboperationDTO(id, started, completed, stream.collect(Collectors.toSet()));
    };
}
