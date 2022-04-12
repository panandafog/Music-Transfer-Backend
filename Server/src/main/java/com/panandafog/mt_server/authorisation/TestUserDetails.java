package com.panandafog.mt_server.authorisation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TestUserDetails {

    @Getter
    private AppUser user;

    @Getter
    private String token;

    public String getHeaderKey() {
        return "Authorization";
    }

    public String getHeaderValue() {
        return "Bearer " + token;
    }
}
