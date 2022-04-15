package com.panandafog.mt_server.music.services;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmAddTracksOperationDTO;
import com.panandafog.mt_server.music.DTO.vk.VKAddTracksOperationDTO;
import com.panandafog.mt_server.music.DTO.vk.VKAddTracksOperationsDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmAddTracksOperationEntity;
import com.panandafog.mt_server.music.entities.vk.VKAddTracksOperationEntity;
import com.panandafog.mt_server.music.repository.last_fm.*;
import com.panandafog.mt_server.music.repository.shared.SharedTrackRepository;
import com.panandafog.mt_server.music.repository.vk.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

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

    public VKAddTracksOperationDTO saveOperation(VKAddTracksOperationDTO addTracksOperationDTO, HttpServletRequest req) {
        AppUser user = userService.whoami(req);
        addTracksOperationDTO.setUser(user);

        VKAddTracksOperationEntity addTracksOperationEntity = addTracksOperationDTO.entity();
        VKAddTracksOperationEntity savedOperation = vkAddTracksOperationRepository.save(addTracksOperationEntity);
        VKAddTracksOperationDTO savedDTO = savedOperation.dto();
        savedDTO.setUser(null);
        return savedDTO;
    }

    public VKAddTracksOperationDTO getOperation(Integer id, HttpServletRequest req) {
        AppUser user = userService.whoami(req);
        VKAddTracksOperationEntity operation = vkAddTracksOperationRepository.findByIdAndUser(
                id,
                user
        ).stream().findFirst().get();
        VKAddTracksOperationDTO operationDTO = operation.dto();
        operationDTO.setUser(null);
        return operationDTO;
    }

    public VKAddTracksOperationsDTO getOperations(Integer page, HttpServletRequest req) {
        AppUser user = userService.whoami(req);
        Page<VKAddTracksOperationEntity> operationPage = vkAddTracksOperationRepository.findByUser(
                user,
                PageRequest.of(page, 2, Sort.by("started"))
        );
        VKAddTracksOperationsDTO dto = new VKAddTracksOperationsDTO();
        dto.setOperations(
                operationPage
                        .stream()
                        .map(VKAddTracksOperationEntity::dto)
                        .map(e -> {
                            e.setUser(null);
                            return e;
                        })
                        .collect(Collectors.toSet())
        );
        dto.setTotal(operationPage.getTotalPages());
        return dto;
    }
}
