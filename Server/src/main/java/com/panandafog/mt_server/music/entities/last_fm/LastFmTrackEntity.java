package com.panandafog.mt_server.music.entities.last_fm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "last_fm_tracks")
@RequiredArgsConstructor
public class LastFmTrackEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
}
