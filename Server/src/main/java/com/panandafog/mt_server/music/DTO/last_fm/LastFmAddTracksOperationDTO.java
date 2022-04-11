package com.panandafog.mt_server.music.DTO.last_fm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.AppUserRole;
import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmAddTracksOperationEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmLikeTracksSuboperationEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmSearchTracksSuboperationEntity;
import com.panandafog.mt_server.music.entities.last_fm.LastFmTrackToLikeEntity;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastFmAddTracksOperationDTO {

    private String id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date started;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date completed;

    private LastFmSearchTracksSuboperationDTO searchSuboperation;
    private LastFmLikeTracksSuboperationDTO likeSuboperation;
    private AppUser user;

    public LastFmAddTracksOperationEntity entity() {
        return new LastFmAddTracksOperationEntity(
                id,
                started,
                completed,
                searchSuboperation.entity(),
                likeSuboperation.entity(),
                user
        );
    };
}
