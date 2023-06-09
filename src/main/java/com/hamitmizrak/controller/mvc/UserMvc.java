package com.hamitmizrak.controller.mvc;
import com.hamitmizrak.data.entity.TokenConfirmationEntity;
import com.hamitmizrak.data.entity.UserEntity;
import com.hamitmizrak.data.repository.ITokenConfirmationRepository;
import com.hamitmizrak.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserMvc {

    private final IUserService iUserService;
    private final ITokenConfirmationRepository iTokenConfirmationRepository;

    //http://localhost:2222/index
    @GetMapping("/index")
    public String rootIndex(){
        return "index";
    }

    //http://localhost:2222/deneme
    @GetMapping("/deneme")
    @ResponseBody
    public String deneme44(){
        iUserService.signUp(UserEntity.builder().name("adı").surname("soyadı").password("şifre").email("hrpmuhendislik44@gmail.com").build());
        //iUserService.sendMail()
        return "Mail Gönderildi";
    }

    //http://localhost:2222/confirm?token=ed45c4a6-c894-4d73-8e57-b6e5adae8db1
    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {
        Optional<TokenConfirmationEntity> optionalConfirmationToken = iTokenConfirmationRepository.findTokenConfirmationEntityByToken(token);
        optionalConfirmationToken.ifPresent(iUserService::mailUserConfirm);
        return "/index";
    }
}
