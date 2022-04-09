package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "last_fm_like_tracks_suboperations")
@RequiredArgsConstructor
public class LastFmLikeTracksSuboperationEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private Date started;

    @Getter
    @Setter
    private Date completed;

    @OneToMany(mappedBy="likeTracksSuboperation")
    @Getter
    @Setter
    private Set<LastFmTrackToLikeEntity> tracksToLike;

    @ManyToMany
    @Getter
    @Setter
    private Set<SharedTrackEntity> notFoundTracks;

    @OneToOne(mappedBy = "likeSuboperation")
    private LastFmAddTracksOperationEntity addTracksOperation;
}