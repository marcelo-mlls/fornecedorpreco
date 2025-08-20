package com.marcelo.api.model;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.sql.ConnectionBuilder;
import java.time.Instant;
import java.util.Objects;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Transactional
@Entity
@Table(name="SGUTP_API_REFRESH_TOKEN")
public class RefreshToken {

    @Id
    @GeneratedValue
    @Column(name = "TOKEN_ID")
    private int id;
    private String token;
    private Instant expiryDate;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users user;

    public RefreshToken(Users user, String token, Instant expiryDate){
        this.user = user;
        this.token = token;
        this.expiryDate = expiryDate;
    }

}
