package GLOW.OldHouseNewHouse.contorller;

import GLOW.OldHouseNewHouse.Data.Dto.Auth.Res.AuthGetLoginRes;
import GLOW.OldHouseNewHouse.Service.AuthService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthContorller {

    private final AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<AuthGetLoginRes> kakaologin(String code) throws IOException {
        return authService.login(code);
    }

    //accessToken 만료시
    @PatchMapping("/login")
    public ResponseEntity<AuthGetLoginRes> kakaologin(Authentication authentication) throws IOException {
        return authService.login(authentication);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<HttpStatus> logout(Authentication authentication){
        return authService.logout(authentication);
    }


}
