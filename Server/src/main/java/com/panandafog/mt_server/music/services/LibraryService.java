package com.panandafog.mt_server.music.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.music.DTO.shared.LibraryRecordDTO;
import com.panandafog.mt_server.music.entities.shared.LibraryRecordEntity;
import com.panandafog.mt_server.music.repository.shared.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final UserService userService;
    private final LibraryRepository libraryRepository;

    public Set<LibraryRecordDTO> saveLibrary(Set<LibraryRecordDTO> libraryRecords, HttpServletRequest req) {
        AppUser user = userService.whoami(req);

        libraryRecords.forEach(record -> record.setUser(user));
        Set<LibraryRecordEntity> entities = libraryRecords.stream().map(LibraryRecordDTO::entity).collect(Collectors.toSet());

        List<LibraryRecordEntity> savedEntities = libraryRepository.saveAll(entities);

        Set<LibraryRecordDTO> savedDTOs = savedEntities.stream().map(LibraryRecordEntity::dto).collect(Collectors.toSet());
        savedDTOs.forEach(dto -> dto.setUser(null));

        return savedDTOs;
    }

    public Set<LibraryRecordDTO> getLibrary(HttpServletRequest req) {
        AppUser user = userService.whoami(req);

        List<LibraryRecordEntity> libraryRecordEntities = libraryRepository.findByUser(user);

        Set<LibraryRecordDTO> libraryRecordDTOs = libraryRecordEntities.stream().map(LibraryRecordEntity::dto).collect(Collectors.toSet());
        libraryRecordDTOs.forEach(dto -> dto.setUser(null));

        return libraryRecordDTOs;
    }
}
