package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.dummy.Dummy;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "last_fm_tracks")
@RequiredArgsConstructor
//@AllArgsConstructor
public class LastFmTrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(unique = false, nullable = true)
    private String serverID;

    @Getter
    @Setter
    @Column(unique = false, nullable = false)
    private String mbid;

    @Getter
    @Setter
    @Column(unique = false, nullable = false)
    private String name;

    @Getter
    @Setter
    private String artist;

    @Getter
    @Setter
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private LastFmTrackToLikeEntity trackToLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private LastFmSearchedTrackEntity lastFmSearchedTrack;

//    @Getter
//    @Setter
//    @ManyToMany(mappedBy = "foundTracks", cascade = CascadeType.ALL)
//    private Set<LastFmSearchedTrackEntity> searchResults;

    public LastFmTrackEntity(Integer id, String serverID, String mbid, String name, String artist, String url) {
        this.id = id;
        this.serverID = serverID;
        this.mbid = mbid;
        this.name = name;
        this.artist = artist;
        this.url = url;
    }

    public LastFmTrackDTO dto() {
        return new LastFmTrackDTO(id, serverID, mbid, name, artist, url);
    };
}
