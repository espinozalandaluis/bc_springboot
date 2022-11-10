package com.bootcamp.model.afp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AfpModel {
    private Integer id;

    @Schema(description = "campo que define el nombre de la aAFP",
            type = "string",
            required = true,
            example = "abc...xyz")
    private String description;
}
