package com.panandafog.mt_server.music.services;

import com.panandafog.mt_server.music.entities.last_fm.*;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import com.panandafog.mt_server.music.repository.last_fm.*;
import com.panandafog.mt_server.music.repository.shared.SharedTrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LastFmService {

    private final LastFmAddTracksOperationRepository lastFmAddTracksOperationRepository;
    private final LastFmLikeTracksSuboperationRepository lastFmLikeTracksSuboperationRepository;
    private final LastFmSearchedTrackRepository lastFmSearchedTrackRepository;
    private final LastFmSearchTracksSuboperationRepository lastFmSearchTracksSuboperationRepository;
    private final LastFmTrackRepository lastFmTrackRepository;
    private final LastFmTrackToLikeRepository lastFmTrackToLikeRepository;

    private final SharedTrackRepository sharedTrackRepository;

    public String saveOperation(LastFmAddTracksOperationEntity addTracksOperationEntity) {
        LastFmLikeTracksSuboperationEntity likeTracksSuboperation = addTracksOperationEntity.getLikeSuboperation();
        LastFmSearchTracksSuboperationEntity searchTracksSuboperation = addTracksOperationEntity.getSearchSuboperation();

        Set<SharedTrackEntity> notFoundTracks = likeTracksSuboperation.getNotFoundTracks();
        Set<LastFmTrackToLikeEntity> tracksToLike = likeTracksSuboperation.getTracksToLike();

        sharedTrackRepository.saveAll(notFoundTracks);

        for (LastFmTrackToLikeEntity track: tracksToLike) {
            lastFmTrackRepository.save(track.getTrack());
            lastFmTrackToLikeRepository.save(track);
        }

        Set<LastFmSearchedTrackEntity> searchedTracks = searchTracksSuboperation.getSearchedTracks();

        for (LastFmSearchedTrackEntity searchedTrack: searchedTracks) {
            lastFmTrackRepository.saveAll(searchedTrack.getTracks());
            lastFmSearchedTrackRepository.save(searchedTrack);
        }

        lastFmSearchTracksSuboperationRepository.save(searchTracksSuboperation);
        lastFmLikeTracksSuboperationRepository.save(likeTracksSuboperation);
        lastFmAddTracksOperationRepository.save(addTracksOperationEntity);
        return "Successful";
    }

//    public LastFmAddTracksOperationEntity getOperation(String id) {
//        lastFmAddTracksOperationRepository.getById(id);
//    }
}
