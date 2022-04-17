package com.panandafog.mt_server.music.entities.shared;

import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmLikeTracksSuboperationEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackToLikeEntity;
import com.panandafog.mt_server.music.entities.vk.VKLikeTracksSuboperationEntity;
import com.panandafog.mt_server.music.entities.vk.VKSearchedTrackEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shared_tracks")
@RequiredArgsConstructor
public class SharedTrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    @CollectionTable(name = "shared_track_artists",
            joinColumns = {
            @JoinColumn(name = "shared_track_id"
                    , referencedColumnName = "id"
                    , foreignKey=@ForeignKey(name="SHARED_TRACK_ARTIST_FK"
                    , foreignKeyDefinition = "FOREIGN KEY (shared_track_id) references public.shared_tracks (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE CASCADE"))
    }
    )
    @Getter
    @Setter
    private List<String> artists;

//    @OneToMany(mappedBy="sharedTrack", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Getter
//    @Setter
//    private Set<SharedTrackArtistEntity> artists;

    @Getter
    @Setter
    @Column(unique = false, nullable = true)
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private VKLikeTracksSuboperationEntity vkLikeTracksSuboperationNotFound;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private VKLikeTracksSuboperationEntity vkLikeTracksSuboperationDuplicate;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private LastFmLikeTracksSuboperationEntity lastFmLikeTracksSuboperationNotFound;

    @OneToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private LastFmSearchedTrackEntity lastFmSearchedTrack;

    @OneToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private VKSearchedTrackEntity vkSearchedTrack;

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
