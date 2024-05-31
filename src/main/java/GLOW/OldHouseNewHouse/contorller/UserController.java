package GLOW.OldHouseNewHouse.contorller;

import GLOW.OldHouseNewHouse.Data.Dto.User.Req.UserPatchReq;
import GLOW.OldHouseNewHouse.Data.Dto.User.Res.UserGetRes;
import GLOW.OldHouseNewHouse.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/mypage/{userId}")
    public ResponseEntity<UserGetRes> getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    @ResponseBody
    @PatchMapping("/mypage/{userId}")
    public RedirectView patchUser(@RequestBody UserPatchReq userPatchReq, @PathVariable Long userId){
        userService.patchUser(userPatchReq,userId);
        return new RedirectView("/mypage/" + userId);
    }

}
