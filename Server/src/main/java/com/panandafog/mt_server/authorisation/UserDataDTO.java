package com.panandafog.mt_server.authorisation;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDataDTO {

    private String username;
    private String email;
    private String password;
    List<AppUserRole> appUserRoles;

}
