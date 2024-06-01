package GLOW.OldHouseNewHouse.Data.Dto.User.Req;

import lombok.Builder;

@Builder
public class HouseApplyResponseDto {
    private String ownerName;
    private String userName;
    private Long userAge;
    private String applyReason;
    private String applyPhotoUrl;
    private Long houseId;
}

