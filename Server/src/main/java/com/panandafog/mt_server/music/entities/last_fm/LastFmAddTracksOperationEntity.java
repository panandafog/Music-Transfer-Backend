package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmAddTracksOperationDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "last_fm_add_tracks_operations")
@RequiredArgsConstructor
public class LastFmAddTracksOperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Date started;

    @Getter
    @Setter
    private Date completed;

    @OneToOne(mappedBy = "addTracksOperation", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private LastFmSearchTracksSuboperationEntity searchSuboperation;

    @OneToOne(mappedBy = "addTracksOperation", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private LastFmLikeTracksSuboperationEntity likeSuboperation;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private AppUser user;

    public LastFmAddTracksOperationEntity(Integer id, Date started, Date completed, LastFmSearchTracksSuboperationEntity searchSuboperation, LastFmLikeTracksSuboperationEntity likeSuboperation, AppUser user) {
        this.id = id;
        this.started = started;
        this.completed = completed;
        this.searchSuboperation = searchSuboperation;
        this.searchSuboperation.setAddTracksOperation(this);
        this.likeSuboperation = likeSuboperation;
        this.likeSuboperation.setAddTracksOperation(this);
        this.user = user;
    }

    public LastFmAddTracksOperationDTO dto() {
        return new LastFmAddTracksOperationDTO(
                id,
                started,
                completed,
                searchSuboperation.dto(),
                likeSuboperation.dto(),
                user
        );
    };
}
