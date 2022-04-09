package com.panandafog.mt_server.music.entities.last_fm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "last_fm_search_tracks_suboperations")
@RequiredArgsConstructor
public class LastFmSearchTracksSuboperationEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private Date started;

    @Getter
    @Setter
    private Date completed;

    @OneToMany(mappedBy="searchTracksSuboperation")
    @Getter
    @Setter
    private Set<LastFmSearchedTrackEntity> searchedTracks;

    @OneToOne(mappedBy = "searchSuboperation")
    private LastFmAddTracksOperationEntity addTracksOperation;
}
