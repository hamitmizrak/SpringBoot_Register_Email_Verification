package com.hamitmizrak.data.entity;

import com.hamitmizrak.role.ERoles;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// LOMBOK
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

// ENTİTY
@Entity
@Table(name="users")
 public  class UserEntity extends BaseEntity implements UserDetails {

    private String name;
    private String surname;
    private String email;
    private String password;


    // ROLES
    @Builder.Default
    private ERoles userRole=ERoles.USER;

    // USER DETAİLS DEFAULT
    @Builder.Default
    private Boolean locked=false;

    @Builder.Default
    private Boolean enabled=false;


    // USER DETAİLS
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    // Hesap süresi dolmasın
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Hesap kiliti
    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    // Kimlik Bilgileri Süresi Dolmadı
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // yetki vermek
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }




}
