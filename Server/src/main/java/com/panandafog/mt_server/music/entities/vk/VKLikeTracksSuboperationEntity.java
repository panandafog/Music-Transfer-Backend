package com.panandafog.mt_server.music.entities.vk;

import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.DTO.vk.VKLikeTracksSuboperationDTO;
import com.panandafog.mt_server.music.DTO.vk.VKTrackToLikeDTO;
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
@Table(name = "vk_like_tracks_suboperations")
@RequiredArgsConstructor
public class VKLikeTracksSuboperationEntity {

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
    private Set<VKTrackToLikeEntity> tracksToLike;

    @OneToMany(mappedBy="vkLikeTracksSuboperationNotFound", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private Set<SharedTrackEntity> notFoundTracks;

    @OneToMany(mappedBy="vkLikeTracksSuboperationDuplicate", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private Set<SharedTrackEntity> duplicates;

    @OneToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private VKAddTracksOperationEntity addTracksOperation;

    public VKLikeTracksSuboperationEntity(Integer id, Date started, Date completed, Set<VKTrackToLikeEntity> tracksToLike, Set<SharedTrackEntity> notFoundTracks,  Set<SharedTrackEntity> duplicates) {
        this.id = id;
        this.started = started;
        this.completed = completed;
        this.tracksToLike = tracksToLike;
        this.notFoundTracks = notFoundTracks;
        this.duplicates = duplicates;

        this.tracksToLike.forEach(t -> t.setLikeTracksSuboperation(this));
        this.notFoundTracks.forEach(t -> t.setVkLikeTracksSuboperationNotFound(this));
        this.notFoundTracks.forEach(t -> t.setVkLikeTracksSuboperationDuplicate(this));
    }

    public VKLikeTracksSuboperationDTO dto() {
        Stream<VKTrackToLikeDTO> tracksToLikeStream = tracksToLike.stream().map(VKTrackToLikeEntity::dto);
        Stream<SharedTrackDTO> notFoundTracksStream = notFoundTracks.stream().map(SharedTrackEntity::dto);
        Stream<SharedTrackDTO> duplicatedTracksStream = duplicates.stream().map(SharedTrackEntity::dto);
        return new VKLikeTracksSuboperationDTO(
                id,
                started,
                completed,
                tracksToLikeStream.collect(Collectors.toSet()),
                notFoundTracksStream.collect(Collectors.toSet()),
                duplicatedTracksStream.collect(Collectors.toSet())
        );
    };
}


