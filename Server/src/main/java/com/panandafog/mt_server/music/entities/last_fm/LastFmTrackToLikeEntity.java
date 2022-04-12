package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackToLikeDTO;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(unique = false, nullable = false)
    @Getter
    @Setter
    private Boolean liked;

    @OneToOne(targetEntity = LastFmTrackEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "track_id")
    @Getter
    @Setter
    private LastFmTrackEntity track;

    @ManyToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private LastFmLikeTracksSuboperationEntity likeTracksSuboperation;

    public LastFmTrackToLikeEntity(Integer id, Boolean liked, LastFmTrackEntity track) {
        this.id = id;
        this.liked = liked;
        this.track = track;
    }

    public LastFmTrackToLikeDTO dto() {
        return new LastFmTrackToLikeDTO(id, liked, track.dto());
    };
}
