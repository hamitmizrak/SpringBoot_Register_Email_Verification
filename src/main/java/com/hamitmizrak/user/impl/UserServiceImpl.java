package com.hamitmizrak.user.impl;

import com.hamitmizrak.bean.PasswordEncodeBean;
import com.hamitmizrak.business.services.ITokenConfirmationServices;
import com.hamitmizrak.business.services.impl.TokenConfirmationImplServicesImpl;
import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.data.entity.UserEntity;
import com.hamitmizrak.data.repository.IUserRepository;

import com.hamitmizrak.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

// LOMBOK
@RequiredArgsConstructor

// SERVICE
@Service
@Component
public class UserServiceImpl implements UserDetailsService, IUserService { //IUserService<UserEntity,TokenConfirmationEntity>

    // INJECTION
    private final IUserRepository iUserRepository;
    private final ITokenConfirmationServices iTokenConfirmationServices;
    private final PasswordEncodeBean passwordEncodeBean;
    private final JavaMailSender mailSender;


    // EMAİL SORGULAMASI YAPIYORUZ
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<UserEntity> userEntity = iUserRepository.findByEmail(email);
        if (userEntity.isPresent()) {
            return userEntity.get();
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
        //NOT: throw new yazdım burada return yazmama gerek yoktur.
    }

    // SIGN UP
    public String signUp(UserEntity userEntity) {
        userEntity.setPassword(passwordEncodeBean.passwordEncoderMethod().encode(userEntity.getPassword()));
        final UserEntity entity = iUserRepository.save(userEntity);
        final TokenConfirmationEntity tokenConfirmationEntity = new TokenConfirmationEntity(entity);
        String token=  iTokenConfirmationServices.createToken(tokenConfirmationEntity);
        sendMail(entity.getEmail(),token);
        return token;
    }

    //MAİL CONFIRM
    public void mailUserConfirm(TokenConfirmationEntity tokenConfirmationEntity) {
        // 1-1 ilişkiden veriyi almak
        final UserEntity userEntity = tokenConfirmationEntity.getUserEntity();
        userEntity.setEnabled(Boolean.TRUE);
        iUserRepository.save(userEntity);
        // mail onaylanması gerçekleşikten sonra token'ı sil
        iTokenConfirmationServices.deleteToken(tokenConfirmationEntity.getId());
    }

    @Override
    public String sendMail(String userMail, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hrpmuhendislik44@gmail.com");
        message.setTo("hrpmuhendislik44@gmail.com");
        String mailSubject = "INC company Üyeliğiniz için son bir adım ";
        message.setSubject(mailSubject);
        String mailContent = "Üyeliğini aktifleştirmek için Lütfen linke tıklayınız." + "http://localhost:2222/confirm?token="+token;
        message.setText(mailContent);
        mailSender.send(message);
        return "mail gönderildi";

    }

    public static void main(String[] args) {
    //UserService userService=new UserService();
    }

}
