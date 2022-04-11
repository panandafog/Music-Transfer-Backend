package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackToLikeDTO;
import com.panandafog.mt_server.utility.Utility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "last_fm_tracks_to_like")
@RequiredArgsConstructor
@AllArgsConstructor
public class LastFmTrackToLikeEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    public LastFmTrackToLikeEntity(String id, Boolean liked, LastFmTrackEntity track) {
        if (Utility.isNullOrEmpty(id)) {
            this.id = Utility.makeID();
        } else {
            this.id = id;
        }
        this.liked = liked;
        this.track = track;
    }

    public LastFmTrackToLikeDTO dto() {
        return new LastFmTrackToLikeDTO(id, liked, track.dto());
    };
}
