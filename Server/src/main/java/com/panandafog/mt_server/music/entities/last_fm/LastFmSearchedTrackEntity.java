package com.panandafog.mt_server.music.entities.last_fm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "last_fm_searched_tracks")
@RequiredArgsConstructor
public class LastFmSearchedTrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
}
