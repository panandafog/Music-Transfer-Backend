package com.panandafog.mt_server.music.entities.shared;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.DTO.shared.LibraryRecordDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "library")
@RequiredArgsConstructor
public class LibraryRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(unique = false, nullable = false)
    private String title;

    @ElementCollection
    @CollectionTable(name = "library_artists",
            joinColumns = {
                    @JoinColumn(name = "library_record_id"
                            , referencedColumnName = "id"
                            , foreignKey=@ForeignKey(name="LIBRARY_RECORD_ARTIST_FK"
                            , foreignKeyDefinition = "FOREIGN KEY (library_record_id) references public.library (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE CASCADE"))
            }
    )
    @Getter
    @Setter
    private List<String> artists;

    @Getter
    @Setter
    @Column(unique = false, nullable = true)
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private AppUser user;

    public LibraryRecordEntity(Integer id, String title, List<String> artists, Integer duration, AppUser user) {
        this.id = id;
        this.title = title;
        this.artists = artists;
        this.duration = duration;
        this.user = user;
    }

    public LibraryRecordDTO dto() {
        return new LibraryRecordDTO(id, title, artists, duration, user);
    };
}
