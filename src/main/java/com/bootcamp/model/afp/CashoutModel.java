package com.bootcamp.model.afp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CashoutModel {
    @NotNull
    @Min(0)
    private int id;

    @Schema(description = "campo que define el ID del membresia.",
            type = "integer",
            required = true,
            example = "1 - 999999")
    @NotNull
    @Min(1)
    private int idMembership;

    @Schema(description = "campo que define el monto asignado al cliente por el afp.",
            type = "decimal",
            required = true,
            example = "1 - 999999")
    @NotNull
    private double amount;
}
