package com.panandafog.mt_server.music.DTO.vk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class VKAddTracksOperationsDTO {

    @Getter
    @Setter
    private Integer total;

    @Getter
    @Setter
    private Set<VKAddTracksOperationDTO> operations;
}
