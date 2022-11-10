package com.bootcamp.model.afp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AfpModel {
    @NotNull
    @Min(0)
    private Integer id;

    @Schema(description = "campo que define el nombre de la AFP",
            type = "string",
            required = true,
            example = "abc...xyz")
    @NotEmpty(message = "campo description es requerido")
    private String description;
}
