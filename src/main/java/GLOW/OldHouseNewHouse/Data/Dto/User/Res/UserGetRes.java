package GLOW.OldHouseNewHouse.Data.Dto.User.Res;

import GLOW.OldHouseNewHouse.Data.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetRes {

    private String email;
    private String profileImgUrl;
    private String userNum;

    public UserGetRes(User user) {
        this.email = user.getEmail();
        this.profileImgUrl = user.getProfileImgUrl();
        this.userNum = user.getUserCallNum();
    }
}
