package com.panandafog.mt_server.music.controllers;

import com.panandafog.mt_server.music.DTO.shared.LibraryRecordDTO;
import com.panandafog.mt_server.music.DTO.vk.VKAddTracksOperationDTO;
import com.panandafog.mt_server.music.DTO.vk.VKAddTracksOperationsDTO;
import com.panandafog.mt_server.music.services.LibraryService;
import com.panandafog.mt_server.music.services.VKService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping()
    public Set<LibraryRecordDTO> saveLibrary(@RequestBody Set<LibraryRecordDTO> records, HttpServletRequest req) {
        return libraryService.saveLibrary(records, req);
    }

    @GetMapping()
    public Set<LibraryRecordDTO> getOperation(HttpServletRequest req) {
        return libraryService.getLibrary(req);
    }
}
