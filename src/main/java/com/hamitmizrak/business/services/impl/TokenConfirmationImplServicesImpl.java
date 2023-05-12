package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.business.services.ITokenConfirmationServices;
import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.data.repository.ITokenConfirmationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// LOMBOK
@Data
@RequiredArgsConstructor

@Service
public class TokenConfirmationImplServicesImpl implements ITokenConfirmationServices<TokenConfirmationEntity> {

    private final ITokenConfirmationRepository iTokenConfirmationRepository;

    // maile gelen linke tıkladğımda çalışacak
    // CREATE
    @Override
    public String createToken(TokenConfirmationEntity tokenConfirmationEntity) {
        TokenConfirmationEntity tokenConfirmationEntity1 =   iTokenConfirmationRepository.save(tokenConfirmationEntity);
        System.out.println("TOKEN: "+tokenConfirmationEntity1.getToken());
        return tokenConfirmationEntity1.getToken();
    }

    // DELETE
    // maile gelen linke tıkladğımda token silinecek
    @Override
    public void deleteToken(Long id) {
        iTokenConfirmationRepository.deleteById(id);
    }



}
