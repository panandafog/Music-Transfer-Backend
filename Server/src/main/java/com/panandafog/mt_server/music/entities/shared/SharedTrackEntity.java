package com.panandafog.mt_server.music.entities.shared;

import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchedTrackEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shared_tracks")
@RequiredArgsConstructor
public class SharedTrackEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private String id;

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
}
