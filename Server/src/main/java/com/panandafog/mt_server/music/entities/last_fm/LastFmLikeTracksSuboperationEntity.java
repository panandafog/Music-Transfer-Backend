package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmLikeTracksSuboperationDTO;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackToLikeDTO;
import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import com.panandafog.mt_server.utility.Utility;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
//    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
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

    public LastFmLikeTracksSuboperationEntity(String id, Date started, Date completed, Set<LastFmTrackToLikeEntity> tracksToLike, Set<SharedTrackEntity> notFoundTracks) {
        if (Utility.isNullOrEmpty(id)) {
            this.id = Utility.makeID();
        } else {
            this.id = id;
        }
        this.started = started;
        this.completed = completed;
        this.tracksToLike = tracksToLike;
        this.notFoundTracks = notFoundTracks;
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