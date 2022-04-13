package com.panandafog.mt_server.music.entities.vk;

import com.panandafog.mt_server.music.DTO.vk.VKSavedItemDTO;
import com.panandafog.mt_server.music.DTO.vk.VKSearchedTrackDTO;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "vk_searched_tracks")
@RequiredArgsConstructor
@AllArgsConstructor
public class VKSearchedTrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(unique = false, nullable = false)
    private Boolean triedToSearchTracks;

    @OneToOne(targetEntity = SharedTrackEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "track_id")
    @Getter
    @Setter
    private SharedTrackEntity trackToSearch;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "vk_searched_track_found_track",
            joinColumns = @JoinColumn(name = "searched_track_id"),
            inverseJoinColumns = @JoinColumn(name = "found_track_id_id")
    )
    @Getter
    @Setter
    private Set<VKSavedItemEntity> foundTracks;

    @ManyToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private VKSearchTracksSuboperationEntity searchTracksSuboperation;

    public VKSearchedTrackEntity(Integer id, Boolean triedToSearchTracks, SharedTrackEntity trackToSearch, Set<VKSavedItemEntity> foundTracks) {
        this.id = id;
        this.triedToSearchTracks = triedToSearchTracks;
        this.trackToSearch = trackToSearch;
        this.foundTracks = foundTracks;
    }

    public VKSearchedTrackDTO dto() {
        Stream<VKSavedItemDTO> stream = foundTracks.stream().map(VKSavedItemEntity::dto);
        return new VKSearchedTrackDTO(id, triedToSearchTracks, trackToSearch.dto(), stream.collect(Collectors.toSet()));
    };
}