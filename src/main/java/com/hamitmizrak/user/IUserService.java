package com.hamitmizrak.user;

import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.data.entity.UserEntity;

public interface IUserService {  // <U,T>

    public String signUp(UserEntity userEntity);
    public void mailUserConfirm(TokenConfirmationEntity tokenConfirmationEntity);

    public String sendMail(String userMail, String token);
}
