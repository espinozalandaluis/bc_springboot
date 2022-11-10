package com.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Where(clause = "bt_status = 1")
@Table(name = "client")
@Data
@AllArgsConstructor
@Getter
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "in_id")
    private int id;

    @Column(name = "vc_name")
    private String name;

    @Column(name = "vc_lastname")
    private String lastName;

    @Column(name = "vc_dni")
    private String dni;

    @Column(name = "vc_phone")
    private String phone;

    @Column(name = "vc_email")
    private String email;

    @Column(name = "bt_status")
    private Boolean status;

    @Column(name = "dt_creation_date")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date  creationDate;

    @Column(name = "vc_creation_user")
    private String creationUser;

    @Column(name = "dt_modification_date")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date  modificationDate;

    @Column(name = "vc_modification_user")
    private String modificationUser;

    public ClientEntity() {
        this.status = true;
        this.creationDate = new Date();
        this.creationUser = System.getProperty("user.name");
    }
}
