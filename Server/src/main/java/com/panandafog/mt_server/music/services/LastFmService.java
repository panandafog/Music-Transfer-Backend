package com.panandafog.mt_server.music.services;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmAddTracksOperationDTO;
import com.panandafog.mt_server.music.entities.last_fm.*;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import com.panandafog.mt_server.music.repository.last_fm.*;
import com.panandafog.mt_server.music.repository.shared.SharedTrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LastFmService {

    private final UserService userService;

    private final LastFmAddTracksOperationRepository lastFmAddTracksOperationRepository;
    private final LastFmLikeTracksSuboperationRepository lastFmLikeTracksSuboperationRepository;
    private final LastFmSearchedTrackRepository lastFmSearchedTrackRepository;
    private final LastFmSearchTracksSuboperationRepository lastFmSearchTracksSuboperationRepository;
    private final LastFmTrackRepository lastFmTrackRepository;
    private final LastFmTrackToLikeRepository lastFmTrackToLikeRepository;

    private final SharedTrackRepository sharedTrackRepository;

    @Transactional
    public String saveOperation(LastFmAddTracksOperationDTO addTracksOperationDTO, HttpServletRequest req) {
        AppUser user = userService.whoami(req);
        addTracksOperationDTO.setUser(user);

        LastFmAddTracksOperationEntity addTracksOperationEntity = addTracksOperationDTO.entity();

        LastFmLikeTracksSuboperationEntity likeTracksSuboperation = addTracksOperationEntity.getLikeSuboperation();
        LastFmSearchTracksSuboperationEntity searchTracksSuboperation = addTracksOperationEntity.getSearchSuboperation();

        Set<SharedTrackEntity> notFoundTracks = likeTracksSuboperation.getNotFoundTracks();
        Set<LastFmTrackToLikeEntity> tracksToLike = likeTracksSuboperation.getTracksToLike();

        LastFmSearchTracksSuboperationEntity savedSearchTracksSuboperation = lastFmSearchTracksSuboperationRepository.save(searchTracksSuboperation);
        LastFmLikeTracksSuboperationEntity savedLikeTracksSuboperation = lastFmLikeTracksSuboperationRepository.save(likeTracksSuboperation);
        lastFmAddTracksOperationRepository.save(addTracksOperationEntity);

        sharedTrackRepository.saveAll(notFoundTracks);
        System.out.println("saved notFoundTracks");

        for (LastFmTrackToLikeEntity track: tracksToLike) {
            lastFmTrackRepository.saveAndFlush(track.getTrack());
            track.setLikeTracksSuboperation(savedLikeTracksSuboperation);
            lastFmTrackToLikeRepository.saveAndFlush(track);
        }

        Set<LastFmSearchedTrackEntity> searchedTracks = searchTracksSuboperation.getSearchedTracks();

        for (LastFmSearchedTrackEntity searchedTrack: searchedTracks) {
            lastFmTrackRepository.saveAllAndFlush(searchedTrack.getTracks());
            searchedTrack.setSearchTracksSuboperation(savedSearchTracksSuboperation);
            lastFmSearchedTrackRepository.saveAndFlush(searchedTrack);
        }

        return "Successful";
    }

    @Transactional
    public LastFmAddTracksOperationDTO getOperation(Integer id, HttpServletRequest req) {
        AppUser user = userService.whoami(req);
        LastFmAddTracksOperationEntity operation = lastFmAddTracksOperationRepository.findByIdAndUser(id, user).stream().findFirst().get();
        LastFmAddTracksOperationDTO operationDTO = operation.dto();
        operationDTO.setUser(null);
        return operationDTO;
    }
}
