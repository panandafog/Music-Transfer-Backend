package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmSearchTracksSuboperationDTO;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmSearchedTrackDTO;
import com.panandafog.mt_server.utility.Utility;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "last_fm_search_tracks_suboperations")
@RequiredArgsConstructor
public class LastFmSearchTracksSuboperationEntity {

    @Id
//    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
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

    public LastFmSearchTracksSuboperationEntity(String id, Date started, Date completed, Set<LastFmSearchedTrackEntity> searchedTracks) {
        if (Utility.isNullOrEmpty(id)) {
            this.id = Utility.makeID();
        } else {
            this.id = id;
        }
        this.started = started;
        this.completed = completed;
        this.searchedTracks = searchedTracks;
    }

    public LastFmSearchTracksSuboperationDTO dto() {
        Stream<LastFmSearchedTrackDTO> stream = searchedTracks.stream().map(LastFmSearchedTrackEntity::dto);
        return new LastFmSearchTracksSuboperationDTO(id, started, completed, stream.collect(Collectors.toSet()));
    };
}
