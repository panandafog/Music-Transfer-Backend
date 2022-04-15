package com.panandafog.mt_server.music.entities.vk;

import com.panandafog.mt_server.music.DTO.vk.VKSearchTracksSuboperationDTO;
import com.panandafog.mt_server.music.DTO.vk.VKSearchedTrackDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "vk_search_tracks_suboperations")
@RequiredArgsConstructor
public class VKSearchTracksSuboperationEntity {

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

    @OneToMany(mappedBy="searchTracksSuboperation", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private Set<VKSearchedTrackEntity> searchedTracks;

    @OneToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private VKAddTracksOperationEntity addTracksOperation;

    public VKSearchTracksSuboperationEntity(Integer id, Date started, Date completed, Set<VKSearchedTrackEntity> searchedTracks) {
        this.id = id;
        this.started = started;
        this.completed = completed;
        this.searchedTracks = searchedTracks;

        this.searchedTracks.forEach(t -> t.setSearchTracksSuboperation(this));
    }

    public VKSearchTracksSuboperationDTO dto() {
        Stream<VKSearchedTrackDTO> stream = searchedTracks.stream().map(VKSearchedTrackEntity::dto);
        return new VKSearchTracksSuboperationDTO(id, started, completed, stream.collect(Collectors.toSet()));
    };
}
