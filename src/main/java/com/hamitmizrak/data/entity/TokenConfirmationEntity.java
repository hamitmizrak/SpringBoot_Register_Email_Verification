package com.hamitmizrak.data.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

// ENTİTY
@Entity
@Table(name="confirmation_token")
public class TokenConfirmationEntity extends BaseEntity {

    private String token;

    // 1 - 1
    // Her kullanıya özel bir tane token verileceği için @OneToOne oluşturdum
    @OneToOne(targetEntity = UserEntity.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name="user_id")
    private UserEntity userEntity;

    //CONSTRUCTOR
    public TokenConfirmationEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        this.token= UUID.randomUUID().toString();
    }
}
