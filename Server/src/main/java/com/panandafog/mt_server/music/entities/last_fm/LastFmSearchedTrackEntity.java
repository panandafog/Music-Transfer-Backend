package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmSearchedTrackDTO;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "last_fm_searched_tracks")
@RequiredArgsConstructor
public class LastFmSearchedTrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(unique = false, nullable = false)
    private Boolean triedToSearchTracks;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "last_fm_searched_track_found_track",
            joinColumns = @JoinColumn(name = "searched_track_id"),
            inverseJoinColumns = @JoinColumn(name = "found_track_id_id")
    )
    @Getter
    @Setter
    private Set<LastFmTrackEntity> foundTracks;

    @ManyToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private LastFmSearchTracksSuboperationEntity searchTracksSuboperation;

    public LastFmSearchedTrackEntity(Integer id, Boolean triedToSearchTracks, Set<LastFmTrackEntity> tracks) {
        this.id = id;
        this.triedToSearchTracks = triedToSearchTracks;
        this.foundTracks = tracks;
    }

    public LastFmSearchedTrackDTO dto() {
        Stream<LastFmTrackDTO> stream = foundTracks.stream().map(LastFmTrackEntity::dto);
        return new LastFmSearchedTrackDTO(id, triedToSearchTracks, stream.collect(Collectors.toSet()));
    };
}
