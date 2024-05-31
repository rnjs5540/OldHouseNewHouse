package GLOW.OldHouseNewHouse.data.dto.user.req;

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

