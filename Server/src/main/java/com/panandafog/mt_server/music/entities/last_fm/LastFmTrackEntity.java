package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmTrackDTO;
import com.panandafog.mt_server.utility.Utility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "last_fm_tracks")
@RequiredArgsConstructor
@AllArgsConstructor
public class LastFmTrackEntity {

    @Id
//    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Getter
    @Setter
    private String id;

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

    @Getter
    @Setter
    @ManyToMany
    private Set<LastFmSearchedTrackEntity> searchResults;

    public LastFmTrackEntity(String id, String mbid, String name, String artist, String url) {
        if (Utility.isNullOrEmpty(id)) {
            this.id = Utility.makeID();
        } else {
            this.id = id;
        }
        this.mbid = mbid;
        this.name = name;
        this.artist = artist;
        this.url = url;
    }

    public LastFmTrackDTO dto() {
        return new LastFmTrackDTO(id, mbid, name, artist, url);
    };
}
