package com.panandafog.mt_server.music.entities.vk;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.DTO.vk.VKAddTracksOperationDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vk_add_tracks_operations")
@RequiredArgsConstructor
public class VKAddTracksOperationEntity {

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
    private VKSearchTracksSuboperationEntity searchSuboperation;

    @OneToOne(mappedBy = "addTracksOperation", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private VKLikeTracksSuboperationEntity likeSuboperation;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private AppUser user;

    public VKAddTracksOperationEntity(Integer id, Date started, Date completed, VKSearchTracksSuboperationEntity searchSuboperation, VKLikeTracksSuboperationEntity likeSuboperation, AppUser user) {
        this.id = id;
        this.started = started;
        this.completed = completed;
        this.searchSuboperation = searchSuboperation;
        this.searchSuboperation.setAddTracksOperation(this);
        this.likeSuboperation = likeSuboperation;
        this.likeSuboperation.setAddTracksOperation(this);
        this.user = user;
    }

    public VKAddTracksOperationDTO dto() {
        return new VKAddTracksOperationDTO(
                id,
                started,
                completed,
                searchSuboperation.dto(),
                likeSuboperation.dto(),
                user
        );
    };
}

