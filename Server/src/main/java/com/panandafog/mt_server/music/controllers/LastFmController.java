package com.panandafog.mt_server.music.controllers;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.UserDataDTO;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.music.DTO.last_fm.LastFmAddTracksOperationDTO;
import com.panandafog.mt_server.music.DTO.shared.SharedTrackDTO;
import com.panandafog.mt_server.music.entities.last_fm.LastFmAddTracksOperationEntity;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import com.panandafog.mt_server.music.services.LastFmService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/lastfm")
@RequiredArgsConstructor
public class LastFmController {

    private final LastFmService lastFmService;
    @Autowired
    private final ModelMapper modelMapper;

//    @PostMapping("/updateOperation")
//    public String updateOperation(@RequestBody UserDataDTO user) {
//        return userService.signin(username, password);
//    }

//    @PostMapping("/testSaveSharedTrack")
//    public String testSaveSharedTrack(@RequestBody SharedTrackDTO sharedTrackDTO) {
//        return lastFmService.testSaveSharedTrack(modelMapper.map(sharedTrackDTO, SharedTrackEntity.class));
//    }

    @PostMapping("/saveOperation")
    public String saveOperation(@RequestBody LastFmAddTracksOperationDTO addTracksOperationDTO, HttpServletRequest req) {
        return lastFmService.saveOperation(addTracksOperationDTO, req);
//        return lastFmService.saveOperation(modelMapper.map(addTracksOperationDTO, LastFmAddTracksOperationEntity.class));
    }

    @GetMapping("/getOperation")
    public LastFmAddTracksOperationDTO saveOperation(@RequestParam Integer id, HttpServletRequest req) {
        return lastFmService.getOperation(id, req);
//        return lastFmService.saveOperation(modelMapper.map(addTracksOperationDTO, LastFmAddTracksOperationEntity.class));
    }
}
