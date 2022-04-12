package com.panandafog.mt_server.music.entities.shared;

import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shared_tracks")
@RequiredArgsConstructor
public class SharedTrackEntity {

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
    private String spotifyID;

    @Getter
    @Setter
    private String lastFmID;

    @Getter
    @Setter
    private String vkID;

    @Getter
    @Setter
    private String vkOwnerID;

    @ElementCollection
    @Getter
    @Setter
    private List<String> artists;

    @Getter
    @Setter
    private Integer duration;

    public SharedTrackEntity(Integer id, String title, String spotifyID, String lastFmID, String vkID, String vkOwnerID, List<String> artists, Integer duration) {
        this.id = id;
        this.title = title;
        this.spotifyID = spotifyID;
        this.lastFmID = lastFmID;
        this.vkID = vkID;
        this.vkOwnerID = vkOwnerID;
        this.artists = artists;
        this.duration = duration;
    }

    public SharedTrackDTO dto() {
        return new SharedTrackDTO(id, title, spotifyID, lastFmID, vkID, vkOwnerID, artists, duration);
    };
}
