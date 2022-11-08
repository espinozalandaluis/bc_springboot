package com.bootcamp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cashout")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CashoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "in_id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "in_id_membership")
    private MembershipEntity membership;

    @Column(name = "db_amount", nullable = false)
    private double amount;

    @Column(name = "dt_cashout_date", nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date cashoutDate;

    @Column(name = "dt_creation_date", nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date  creationDate;

    @Column(name = "vc_creation_user", nullable = false, length = 50)
    private String creationUser;

    @Column(name = "dt_modification_date")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date  modificationDate;

    @Column(name = "vc_modification_user")
    private String modificationUser;
}
