package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmSearchedTrackDTO;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackDTO;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(unique = false, nullable = false)
    private Boolean triedToSearchTracks;

    @OneToOne(mappedBy = "lastFmSearchedTrack", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private SharedTrackEntity trackToSearch;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "last_fm_searched_track_found_track",
//            joinColumns = @JoinColumn(name = "searched_track_id"),
//            inverseJoinColumns = @JoinColumn(name = "found_track_id_id")
//    )
    @OneToMany(mappedBy="lastFmSearchedTrack", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private Set<LastFmTrackEntity> foundTracks;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private LastFmSearchTracksSuboperationEntity searchTracksSuboperation;

    public LastFmSearchedTrackEntity(Integer id, Boolean triedToSearchTracks, SharedTrackEntity trackToSearch, Set<LastFmTrackEntity> tracks) {
        this.id = id;
        this.triedToSearchTracks = triedToSearchTracks;
        this.trackToSearch = trackToSearch;
        this.foundTracks = tracks;

        this.trackToSearch.setLastFmSearchedTrack(this);
        this.foundTracks.forEach(t -> t.setLastFmSearchedTrack(this));
    }

    public LastFmSearchedTrackDTO dto() {
        Stream<LastFmTrackDTO> stream = foundTracks.stream().map(LastFmTrackEntity::dto);
        return new LastFmSearchedTrackDTO(id, triedToSearchTracks, trackToSearch.dto(), stream.collect(Collectors.toSet()));
    };
}
