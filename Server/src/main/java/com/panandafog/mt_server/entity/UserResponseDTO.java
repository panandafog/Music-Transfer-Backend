package com.panandafog.mt_server.entity;

import java.util.List;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    List<AppUserRole> appUserRoles;
}
