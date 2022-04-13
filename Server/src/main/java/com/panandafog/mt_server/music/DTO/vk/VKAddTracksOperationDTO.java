package com.panandafog.mt_server.music.DTO.vk;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmLikeTracksSuboperationDTO;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmSearchTracksSuboperationDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmAddTracksOperationEntity;
import com.panandafog.mt_server.music.entities.vk.VKAddTracksOperationEntity;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VKAddTracksOperationDTO {

    @Getter
    @Setter
    private Integer id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Getter
    @Setter
    private Date started;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Getter
    @Setter
    private Date completed;

    @Getter
    @Setter
    private VKSearchTracksSuboperationDTO searchSuboperation;

    @Getter
    @Setter
    private VKLikeTracksSuboperationDTO likeSuboperation;

    @Getter
    @Setter
    private AppUser user;

    public VKAddTracksOperationEntity entity() {
        return new VKAddTracksOperationEntity(
                id,
                started,
                completed,
                searchSuboperation.entity(),
                likeSuboperation.entity(),
                user
        );
    };
}
