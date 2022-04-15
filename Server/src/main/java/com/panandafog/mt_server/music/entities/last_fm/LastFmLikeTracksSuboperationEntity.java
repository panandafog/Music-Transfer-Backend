package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmLikeTracksSuboperationDTO;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackToLikeDTO;
import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "last_fm_like_tracks_suboperations")
@RequiredArgsConstructor
public class LastFmLikeTracksSuboperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Date started;

    @Getter
    @Setter
    private Date completed;

    @OneToMany(mappedBy="likeTracksSuboperation", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private Set<LastFmTrackToLikeEntity> tracksToLike;

    @OneToMany(mappedBy="lastFmLikeTracksSuboperationNotFound", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private Set<SharedTrackEntity> notFoundTracks;

    @OneToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private LastFmAddTracksOperationEntity addTracksOperation;

    public LastFmLikeTracksSuboperationEntity(Integer id, Date started, Date completed, Set<LastFmTrackToLikeEntity> tracksToLike, Set<SharedTrackEntity> notFoundTracks) {
        this.id = id;
        this.started = started;
        this.completed = completed;
        this.tracksToLike = tracksToLike;
        this.notFoundTracks = notFoundTracks;

        this.tracksToLike.forEach(t -> t.setLikeTracksSuboperation(this));
        this.notFoundTracks.forEach(t -> t.setLastFmLikeTracksSuboperationNotFound(this));
    }

    public LastFmLikeTracksSuboperationDTO dto() {
        Stream<LastFmTrackToLikeDTO> tracksToLikeStream = tracksToLike.stream().map(LastFmTrackToLikeEntity::dto);
        Stream<SharedTrackDTO> notFoundTracksStream = notFoundTracks.stream().map(SharedTrackEntity::dto);
        return new LastFmLikeTracksSuboperationDTO(
                id,
                started,
                completed,
                tracksToLikeStream.collect(Collectors.toSet()),
                notFoundTracksStream.collect(Collectors.toSet())
        );
    };
}