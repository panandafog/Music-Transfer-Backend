package com.panandafog.mt_server.music.entities.last_fm;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmAddTracksOperationDTO;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import com.panandafog.mt_server.utility.Utility;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "last_fm_add_tracks_operations")
@RequiredArgsConstructor
public class LastFmAddTracksOperationEntity {

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "search_suboperation_id", referencedColumnName = "id")
    @Getter
    @Setter
    private LastFmSearchTracksSuboperationEntity searchSuboperation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "like_suboperation_id", referencedColumnName = "id")
    @Getter
    @Setter
    private LastFmLikeTracksSuboperationEntity likeSuboperation;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @Getter
    @Setter
    private AppUser user;

    public LastFmAddTracksOperationEntity(String id, Date started, Date completed, LastFmSearchTracksSuboperationEntity searchSuboperation, LastFmLikeTracksSuboperationEntity likeSuboperation, AppUser user) {
        if (Utility.isNullOrEmpty(id)) {
            this.id = Utility.makeID();
        } else {
            this.id = id;
        }
        this.started = started;
        this.completed = completed;
        this.searchSuboperation = searchSuboperation;
        this.likeSuboperation = likeSuboperation;
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
