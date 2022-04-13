package com.panandafog.mt_server.music.controllers;

import com.panandafog.mt_server.music.DTO.vk.VKAddTracksOperationDTO;
import com.panandafog.mt_server.music.services.OperationsService;
import com.panandafog.mt_server.music.services.VKService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationsController {

    private final OperationsService service;

    @GetMapping
    public List getOperation(HttpServletRequest req) {
        return service.getAllOperations(req);
    }
}
