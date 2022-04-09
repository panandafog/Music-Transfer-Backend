package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "last_fm_add_tracks_operations")
@RequiredArgsConstructor
public class LastFmAddTracksOperationEntity {

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "search_suboperation_id", referencedColumnName = "id")
    private LastFmSearchTracksSuboperationEntity searchSuboperation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "like_suboperation_id", referencedColumnName = "id")
    private LastFmLikeTracksSuboperationEntity likeSuboperation;
}
