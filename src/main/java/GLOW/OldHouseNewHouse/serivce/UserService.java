package GLOW.OldHouseNewHouse.serivce;

import GLOW.OldHouseNewHouse.data.dto.user.req.UserPatchReq;
import GLOW.OldHouseNewHouse.data.dto.user.res.UserGetRes;
import GLOW.OldHouseNewHouse.data.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final GLOW.OldHouseNewHouse.repository.UsersRepository usersRepository;

    public ResponseEntity<UserGetRes> getUser(Long userId){
        Users users = usersRepository.findById(userId).orElse(null);
        if(users == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(new UserGetRes(users), HttpStatus.OK);
    }

    public void patchUser(UserPatchReq userPatchReq,Long userId){
        Users users = usersRepository.findById(userId).orElse(null);

        if(users == null)
            return;

        users.setEmail(userPatchReq.getEmail());
        users.setProfileImgUrl(userPatchReq.getProfileImgUrl());
        users.setUserCallNum(userPatchReq.getUserNum());

        usersRepository.save(users);

    }

}
