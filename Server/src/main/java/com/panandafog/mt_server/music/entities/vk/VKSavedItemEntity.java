package com.panandafog.mt_server.music.entities.vk;

import com.panandafog.mt_server.music.DTO.vk.VKSavedItemDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vk_saved_items")
@RequiredArgsConstructor
@AllArgsConstructor
public class VKSavedItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(unique = false, nullable = false)
    private String title;

    @Getter
    @Setter
    private String artist;

    @Getter
    @Setter
    private Integer ownerID;

    @Getter
    @Setter
    private Integer duration;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "foundTracks", cascade = CascadeType.ALL)
    private Set<VKSearchedTrackEntity> searchResults;

    public VKSavedItemEntity(Integer id, String title, String artist, Integer ownerID, Integer duration) {
        this.id = id;
                this.title = title;
                this.artist = artist;
                this.ownerID = ownerID;
                this.duration = duration;
    }

    public VKSavedItemDTO dto() {
        return new VKSavedItemDTO(id, title, artist, ownerID, duration);
    };
}
