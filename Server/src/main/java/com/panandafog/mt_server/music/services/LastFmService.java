package com.panandafog.mt_server.music.services;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.music.DTO.last_fm.*;
import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
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

    public String saveOperation(LastFmAddTracksOperationDTO addTracksOperationDTO, HttpServletRequest req) {
        AppUser user = userService.whoami(req);
        addTracksOperationDTO.setUser(user);

        LastFmAddTracksOperationEntity addTracksOperationEntity = addTracksOperationDTO.entity();

        LastFmLikeTracksSuboperationDTO likeTracksSuboperation = addTracksOperationDTO.getLikeSuboperation();
        LastFmSearchTracksSuboperationDTO searchTracksSuboperation = addTracksOperationDTO.getSearchSuboperation();

        Set<SharedTrackDTO> notFoundTracks = likeTracksSuboperation.getNotFoundTracks();
        Set<LastFmTrackToLikeDTO> tracksToLike = likeTracksSuboperation.getTracksToLike();

        saveSharedTracks(notFoundTracks);
        saveTracksToLike(tracksToLike);

        Set<LastFmSearchedTrackDTO> searchedTracks = searchTracksSuboperation.getSearchedTracks();
        saveSearchedTracks(searchedTracks);

        LastFmSearchTracksSuboperationEntity savedSearchTracksSuboperationEntity = saveSearchSuboperation(searchTracksSuboperation.entity());
        LastFmLikeTracksSuboperationEntity savedLikeTracksSuboperationEntity = saveLikeSuboperation(likeTracksSuboperation.entity());
        LastFmAddTracksOperationEntity savedAddTracksOperation = saveAddOperation(addTracksOperationEntity);

        for (LastFmTrackToLikeDTO track: tracksToLike) {
            LastFmTrackToLikeEntity trackEntity = track.entity();
            trackEntity.setLikeTracksSuboperation(savedLikeTracksSuboperationEntity);
            lastFmTrackToLikeRepository.saveAndFlush(trackEntity);
        }

        for (LastFmSearchedTrackDTO searchedTrack: searchedTracks) {
            LastFmSearchedTrackEntity searchedTrackEntity = searchedTrack.entity();
            searchedTrackEntity.setSearchTracksSuboperation(savedSearchTracksSuboperationEntity);
            lastFmSearchedTrackRepository.saveAndFlush(searchedTrackEntity);
        }

        return "Successful";
    }

    @Transactional
    public LastFmSearchTracksSuboperationEntity saveSearchSuboperation(
            LastFmSearchTracksSuboperationEntity savedSearchTracksSuboperationEntity
    ) {
        return lastFmSearchTracksSuboperationRepository.saveAndFlush(savedSearchTracksSuboperationEntity);
    }

    @Transactional
    public LastFmLikeTracksSuboperationEntity saveLikeSuboperation(
            LastFmLikeTracksSuboperationEntity savedLikeTracksSuboperationEntity
    ) {
        return lastFmLikeTracksSuboperationRepository.saveAndFlush(savedLikeTracksSuboperationEntity);
    }

    @Transactional
    public LastFmAddTracksOperationEntity saveAddOperation(
            LastFmAddTracksOperationEntity addTracksOperationEntity
    ) {
        return lastFmAddTracksOperationRepository.saveAndFlush(addTracksOperationEntity);
    }

    @Transactional
    public Void saveTracksToLike(Set<LastFmTrackToLikeDTO> tracksToLike) {
        for (LastFmTrackToLikeDTO track: tracksToLike) {
            LastFmTrackToLikeEntity trackEntity = track.entity();
            saveTrack(track.getTrack());
            lastFmTrackToLikeRepository.saveAndFlush(trackEntity);
        }
        return null;
    }

    @Transactional
    public Void saveSearchedTracks(Set<LastFmSearchedTrackDTO> searchedTracks) {
        for (LastFmSearchedTrackDTO searchedTrack: searchedTracks) {
            LastFmSearchedTrackEntity searchedTrackEntity = searchedTrack.entity();
            saveLastFmTracks(searchedTrackEntity.getFoundTracks());
            lastFmSearchedTrackRepository.saveAndFlush(searchedTrackEntity);
        }
        return null;
    }

    public Void saveSharedTracks(Set<SharedTrackDTO> tracks) {
        for (SharedTrackDTO track: tracks) {
            saveSharedTrack(track.entity());
        }
        return null;
    }

    @Transactional
    public Void saveSharedTrack(SharedTrackEntity track) {
        sharedTrackRepository.saveAndFlush(track);
        return null;
    }

    @Transactional
    public Void saveLastFmTracks(Set<LastFmTrackEntity> tracks) {
        lastFmTrackRepository.saveAllAndFlush(tracks);
        return null;
    }

    @Transactional
    public Void saveTrack(LastFmTrackDTO track) {
        lastFmTrackRepository.saveAndFlush(track.entity());
        return null;
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
