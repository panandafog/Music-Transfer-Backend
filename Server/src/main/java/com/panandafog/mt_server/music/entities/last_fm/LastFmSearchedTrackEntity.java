package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmSearchedTrackDTO;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackDTO;
import com.panandafog.mt_server.utility.Utility;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "last_fm_searched_tracks")
@RequiredArgsConstructor
public class LastFmSearchedTrackEntity {

    @Id
//    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(unique = false, nullable = false)
    private Boolean triedToSearchTracks;

    @ManyToMany
    @JoinTable(
            name = "last_fm_searched_track_found_track",
            joinColumns = @JoinColumn(name = "searched_track_id"),
            inverseJoinColumns = @JoinColumn(name = "found_track_id_id")
    )
    @Getter
    @Setter
    private Set<LastFmTrackEntity> foundTracks;

    @ManyToOne
//    @JoinColumn(name="cart_id", nullable=false)
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
