package GLOW.OldHouseNewHouse.data.dto.user.res;

import GLOW.OldHouseNewHouse.data.entity.Users;
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

    public UserGetRes(Users users) {
        this.email = users.getEmail();
        this.profileImgUrl = users.getProfileImgUrl();
        this.userNum = users.getUserCallNum();
    }
}
