package com.bootcamp.model.afp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipModel {
    private int id;

    @Schema(description = "campo que define el ID del cliente.",
            type = "integer",
            required = true,
            example = "1 - 999999")
    private int idCliente;

    @Schema(description = "campo que define el ID del afp.",
            type = "integer",
            required = true,
            example = "1 - 999999")
    private int idAfp;

    @Schema(description = "campo que define el nro de cuenta del cliente.",
            type = "string",
            required = true,
            example = "CTA999999999999")
    private String account;

    @Schema(description = "campo que define el monto a retirar del cliente.",
            type = "decimal",
            required = true,
            example = "1 - 999999.99")
    private double amount;
}
