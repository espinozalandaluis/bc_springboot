package com.bootcamp.model.afp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashoutModel {
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

    @Schema(description = "campo que define el monto asignado al cliente por el afp.",
            type = "decimal",
            required = true,
            example = "1 - 999999")
    private double amount;
}
