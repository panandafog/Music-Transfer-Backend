package com.panandafog.mt_server.music.controllers;

import com.panandafog.mt_server.music.DTO.vk.VKAddTracksOperationDTO;
import com.panandafog.mt_server.music.DTO.vk.VKAddTracksOperationsDTO;
import com.panandafog.mt_server.music.services.VKService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/vk")
@RequiredArgsConstructor
public class VKController {

    private final VKService vkService;

    @PostMapping("/saveOperation")
    public String saveOperation(@RequestBody VKAddTracksOperationDTO addTracksOperationDTO, HttpServletRequest req) {
        return vkService.saveOperation(addTracksOperationDTO, req);
    }

    @GetMapping("/getOperation")
    public VKAddTracksOperationDTO getOperation(@RequestParam Integer id, HttpServletRequest req) {
        return vkService.getOperation(id, req);
    }

    @GetMapping("/getOperations")
    public VKAddTracksOperationsDTO getOperations(@RequestParam Integer page, HttpServletRequest req) {
        return vkService.getOperations(page, req);
    }
}
