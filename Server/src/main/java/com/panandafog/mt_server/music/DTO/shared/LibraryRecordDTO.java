package com.panandafog.mt_server.music.DTO.shared;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.entities.shared.LibraryRecordEntity;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryRecordDTO {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private List<String> artists;

    @Getter
    @Setter
    private Integer duration;

    @Getter
    @Setter
    private AppUser user;

    public LibraryRecordEntity entity() {
        return new LibraryRecordEntity(id, title, artists, duration, user);
    };
}
