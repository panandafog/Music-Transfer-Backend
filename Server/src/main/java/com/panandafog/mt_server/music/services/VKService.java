package com.panandafog.mt_server.music.services;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmAddTracksOperationDTO;
import com.panandafog.mt_server.music.DTO.vk.VKAddTracksOperationDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmAddTracksOperationEntity;
import com.panandafog.mt_server.music.entities.vk.VKAddTracksOperationEntity;
import com.panandafog.mt_server.music.repository.last_fm.*;
import com.panandafog.mt_server.music.repository.shared.SharedTrackRepository;
import com.panandafog.mt_server.music.repository.vk.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class VKService {

    private final UserService userService;

    private final VKAddTracksOperationRepository vkAddTracksOperationRepository;
    private final VKLikeTracksSuboperationRepository vkLikeTracksSuboperationRepository;
    private final VKSavedItemRepository vkSavedItemRepository;
    private final VKSearchedTrackRepository vkSearchedTrackRepository;
    private final VKSearchTracksSuboperationRepository vkSearchTracksSuboperationRepository;
    private final VKTrackToLikeRepository vkTrackToLikeRepository;

    private final SharedTrackRepository sharedTrackRepository;

    public String saveOperation(VKAddTracksOperationDTO addTracksOperationDTO, HttpServletRequest req) {
        AppUser user = userService.whoami(req);
        addTracksOperationDTO.setUser(user);

        VKAddTracksOperationEntity addTracksOperationEntity = addTracksOperationDTO.entity();

        vkAddTracksOperationRepository.save(addTracksOperationEntity);

        return "Successful";
    }

    public VKAddTracksOperationDTO getOperation(Integer id, HttpServletRequest req) {
        AppUser user = userService.whoami(req);
        VKAddTracksOperationEntity operation = vkAddTracksOperationRepository.findByIdAndUser(id, user).stream().findFirst().get();
        VKAddTracksOperationDTO operationDTO = operation.dto();
        operationDTO.setUser(null);
        return operationDTO;
    }
}
