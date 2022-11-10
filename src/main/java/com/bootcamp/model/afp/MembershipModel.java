package com.bootcamp.model.afp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipModel {
    private int id;

    @Schema(description = "campo que define el ID de la membresia.",
            type = "integer",
            required = true,
            example = "1 - 999999")
    private int idMembership;

    @Schema(description = "campo que define el monto a retirar del cliente.",
            type = "decimal",
            required = true,
            example = "1 - 999999.99")
    private double amount;

    @Schema(description = "campo que define la fecha del retiro.",
            type = "date",
            required = true,
            example = "01/01/1900")
    @JsonFormat(pattern="dd/MM/yyyy")
    private String cashoutDate;
}
