package com.bootcamp.model.afp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class ClientModel {
    @NotNull
    @Min(0)
    private Integer id;

    @Schema(description = "campo que define el nombre del cliente.",
            type = "string",
            required = true,
            example = "abc...xyz")
    @NotEmpty(message = "campo name es requerido")
    private String name;

    @Schema(description = "campo que define el apellido del cliente.",
            type = "string",
            required = true,
            example = "abc...xyz")
    @NotEmpty(message = "campo lastname es requerido")
    private String lastName;

    @Schema(description = "campo que define el DNI del cliente.",
            type = "string",
            required = true,
            example = "12345678")
    @NotEmpty(message = "campo dni es requerido")
    @Size(min = 8, max = 12)
    private String dni;

    @Schema(description = "campo que define el telefono del cliente.",
            type = "string",
            required = true,
            example = "55555555")
    @NotEmpty(message = "campo phone es requerido")
    private String phone;

    @Schema(description = "campo que define el correo del cliente.",
            type = "string",
            required = true,
            example = "abc@xyz.com")
    @NotEmpty(message = "campo correo es requerido")
    @Email(message = "el formato de correo es incorrecto")
    private String email;
}
