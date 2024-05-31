package GLOW.OldHouseNewHouse.Controller;

import GLOW.OldHouseNewHouse.Data.Dto.User.Req.UserPatchReq;
import GLOW.OldHouseNewHouse.Data.Dto.User.Res.UserGetRes;
import GLOW.OldHouseNewHouse.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/mypage/{id}")
    public ResponseEntity<UserGetRes> getUser(Authentication authentication){
        return userService.getUser(authentication);
    }

    @PatchMapping("/mypage/{id}")
    public ResponseEntity<UserGetRes> patchUser(@RequestBody UserPatchReq userPatchReq,Authentication authentication){
        return userService.patchUser(userPatchReq,authentication);
    }

}
