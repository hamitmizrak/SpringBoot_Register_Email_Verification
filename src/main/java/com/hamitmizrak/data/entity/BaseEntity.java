package com.hamitmizrak.data.entity;

import com.hamitmizrak.role.ERoles;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

//LOMBOK
@Getter
@Setter

//SUPERCLASS
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    //private Date systemCreatedDate;
    private LocalDate systemCreatedDate;

    public BaseEntity() {
        this.systemCreatedDate=LocalDate.now(); //şimdiki zamanı versin
    }
}