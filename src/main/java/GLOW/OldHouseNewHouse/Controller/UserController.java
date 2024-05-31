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

    @GetMapping("/mypage/{userId}")
    public ResponseEntity<UserGetRes> getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    @PatchMapping("/mypage/{userId}")
    public ResponseEntity<UserGetRes> patchUser(@RequestBody UserPatchReq userPatchReq,@PathVariable Long userId){
        return userService.patchUser(userPatchReq,userId);
    }

}
