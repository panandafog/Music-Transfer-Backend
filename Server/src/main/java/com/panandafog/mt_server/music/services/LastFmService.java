package com.panandafog.mt_server.music.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.music.DTO.last_fm.*;
import com.panandafog.mt_server.music.entities.last_fm.*;
import com.panandafog.mt_server.music.repository.last_fm.*;
import com.panandafog.mt_server.music.repository.shared.SharedTrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public LastFmAddTracksOperationDTO saveOperation(LastFmAddTracksOperationDTO addTracksOperationDTO, HttpServletRequest req) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(addTracksOperationDTO);
            //System.out.println(json);
        } catch (JsonProcessingException e) {
            System.out.println("Exception during json");
            e.printStackTrace();
        }
        System.out.println();

        AppUser user = userService.whoami(req);
        addTracksOperationDTO.setUser(user);

        LastFmAddTracksOperationEntity addTracksOperationEntity = addTracksOperationDTO.entity();

        LastFmAddTracksOperationEntity savedEntity = lastFmAddTracksOperationRepository.save(addTracksOperationEntity);
        LastFmAddTracksOperationDTO savedDTO = savedEntity.dto();
        savedDTO.setUser(null);
        return savedDTO;
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
