package com.panandafog.mt_server.music.entities.vk;

import com.panandafog.mt_server.music.DTO.vk.VKTrackToLikeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vk_tracks_to_like")
@RequiredArgsConstructor
@AllArgsConstructor
public class VKTrackToLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(unique = false, nullable = false)
    private Boolean liked;

    @OneToOne(targetEntity = VKSavedItemEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "track_id")
    @Getter
    @Setter
    private VKSavedItemEntity savedItem;

    @ManyToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private VKLikeTracksSuboperationEntity likeTracksSuboperation;

    public VKTrackToLikeEntity(Integer id, Boolean liked, VKSavedItemEntity savedItem) {
        this.id = id;
        this.liked = liked;
        this.savedItem = savedItem;
    }

    public VKTrackToLikeDTO dto() {
        return new VKTrackToLikeDTO(id, liked, savedItem.dto());
    };
}
