package com.panandafog.mt_server.music.entities.last_fm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "last_fm_tracks_to_like")
@RequiredArgsConstructor
public class LastFmTrackToLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private String id;

    @Column(unique = false, nullable = false)
    @Getter
    @Setter
    private Boolean liked;

    @OneToOne(targetEntity = LastFmTrackEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "track_id")
    @Getter
    @Setter
    private LastFmTrackEntity track;

    @ManyToOne
//    @JoinColumn(name="cart_id", nullable=false)
    private LastFmLikeTracksSuboperationEntity likeTracksSuboperation;
}
