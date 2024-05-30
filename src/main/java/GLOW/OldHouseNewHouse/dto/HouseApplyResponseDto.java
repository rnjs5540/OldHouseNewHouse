package GLOW.OldHouseNewHouse.dto;

import lombok.Builder;

@Builder
public class HouseApplyResponseDto {
    private String ownerName;
    private String userName;
    private int userAge;
    private String applyReason;
    private String applyPhotoUrl;
    private Long houseId;
}
