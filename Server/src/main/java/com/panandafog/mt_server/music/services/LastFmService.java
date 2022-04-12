package com.panandafog.mt_server.music.services;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.music.DTO.last_fm.*;
import com.panandafog.mt_server.music.entities.last_fm.*;
import com.panandafog.mt_server.music.repository.last_fm.*;
import com.panandafog.mt_server.music.repository.shared.SharedTrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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

        lastFmAddTracksOperationRepository.save(addTracksOperationEntity);

        return "Successful";
    }

    public LastFmAddTracksOperationDTO getOperation(Integer id, HttpServletRequest req) {
        AppUser user = userService.whoami(req);
        LastFmAddTracksOperationEntity operation = lastFmAddTracksOperationRepository.findByIdAndUser(id, user).stream().findFirst().get();
        LastFmAddTracksOperationDTO operationDTO = operation.dto();
        operationDTO.setUser(null);
        return operationDTO;
    }
}
