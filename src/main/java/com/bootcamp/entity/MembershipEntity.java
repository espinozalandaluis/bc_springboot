package com.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Where(clause = "bt_status = 1")
@Table(name = "membership")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MembershipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "in_id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "in_id_client")
    private ClientEntity client;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "in_id_afp")
    private AfpEntity afp;

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
}
