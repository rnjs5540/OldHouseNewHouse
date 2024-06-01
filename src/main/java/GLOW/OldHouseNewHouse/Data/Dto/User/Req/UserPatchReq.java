package GLOW.OldHouseNewHouse.Data.Dto.User.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPatchReq {

    private String email;
    private String profileImgUrl;
    private String userNum;


}
