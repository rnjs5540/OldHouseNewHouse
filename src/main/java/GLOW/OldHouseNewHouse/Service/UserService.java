package GLOW.OldHouseNewHouse.Service;

import GLOW.OldHouseNewHouse.Data.Dto.User.Req.UserPatchReq;
import GLOW.OldHouseNewHouse.Data.Dto.User.Res.UserGetRes;
import GLOW.OldHouseNewHouse.Data.Entity.User;
import GLOW.OldHouseNewHouse.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<UserGetRes> getUser(Long userId){
        User user=userRepository.findById(userId).orElse(null);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(new UserGetRes(user), HttpStatus.OK);
    }

    public void patchUser(UserPatchReq userPatchReq,Long userId){
        User user = userRepository.findById(userId).orElse(null);

        if(user == null)
            return;

        user.setEmail(userPatchReq.getEmail());
        user.setProfileImgUrl(userPatchReq.getProfileImgUrl());
        user.setUserNum(userPatchReq.getUserNum());

        userRepository.save(user);

    }

}