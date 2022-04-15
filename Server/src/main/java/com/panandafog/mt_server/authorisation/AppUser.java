package com.panandafog.mt_server.authorisation;

import com.panandafog.mt_server.music.entities.last_fm.LastFmAddTracksOperationEntity;
import com.panandafog.mt_server.music.entities.shared.SharedTrackEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Getter
  @Setter
  private Integer id;

  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  @Column(unique = true, nullable = false)
  @Getter
  @Setter
  private String username;

  @Column(unique = true, nullable = false)
  @Getter
  @Setter
  private String email;

  @Size(min = 8, message = "Minimum password length: 8 characters")
  @Getter
  @Setter
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  @Getter
  @Setter
  private List<AppUserRole> appUserRoles;

  @Column(name = "enabled")
  @Getter
  @Setter
  private boolean enabled;

  @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
  @Getter
  @Setter
  private Set<LastFmAddTracksOperationEntity> lastFmAddTracksOperations;

  public AppUser() {
    super();
    this.enabled = false;
  }
}
