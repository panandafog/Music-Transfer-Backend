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
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @Column(unique = false, nullable = false)
    private Boolean triedToSearchTracks;

    @ManyToMany
    @Getter
    @Setter
    private Set<LastFmTrackEntity> tracks;

    @ManyToOne
//    @JoinColumn(name="cart_id", nullable=false)
    private LastFmSearchTracksSuboperationEntity searchTracksSuboperation;

    public LastFmSearchedTrackEntity(String id, Boolean triedToSearchTracks, Set<LastFmTrackEntity> tracks) {
        if (Utility.isNullOrEmpty(id)) {
            this.id = Utility.makeID();
        } else {
            this.id = id;
        }
        this.triedToSearchTracks = triedToSearchTracks;
        this.tracks = tracks;
    }

    public LastFmSearchedTrackDTO dto() {
        Stream<LastFmTrackDTO> stream = tracks.stream().map(LastFmTrackEntity::dto);
        return new LastFmSearchedTrackDTO(id, triedToSearchTracks, stream.collect(Collectors.toSet()));
    };
}
