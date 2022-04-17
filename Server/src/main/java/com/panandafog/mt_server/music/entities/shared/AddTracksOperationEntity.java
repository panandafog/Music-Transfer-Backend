package com.panandafog.mt_server.music.entities.shared;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedStoredProcedureQuery(
        name = "get_all_operations",
        procedureName = "get_all_operations",
        parameters = {
                @StoredProcedureParameter(name = "selected_user_id", mode = ParameterMode.IN, type = Integer.class)
        },
        resultClasses = { AddTracksOperationEntity.class }
)

public class AddTracksOperationEntity {

    @Id
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date started;

    @Getter
    @Setter
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date completed;

    @Getter
    @Setter
    private String type;
}
