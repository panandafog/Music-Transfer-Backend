package com.panandafog.mt_server.music.controllers;

import com.panandafog.mt_server.music.DTO.last_fm.LastFmAddTracksOperationDTO;
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

    @PostMapping("/saveOperation")
    public String saveOperation(@RequestBody LastFmAddTracksOperationDTO addTracksOperationDTO, HttpServletRequest req) {
        return lastFmService.saveOperation(addTracksOperationDTO, req);
    }

    @GetMapping("/getOperation")
    public LastFmAddTracksOperationDTO getOperation(@RequestParam Integer id, HttpServletRequest req) {
        return lastFmService.getOperation(id, req);
    }
}
