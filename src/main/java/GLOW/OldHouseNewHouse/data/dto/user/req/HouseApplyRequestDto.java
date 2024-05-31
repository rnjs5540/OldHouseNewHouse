package GLOW.OldHouseNewHouse.data.dto.user.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HouseApplyRequestDto {
    private Long houseId;
    private Long userId;
    private String applyReason;
    private String appealPhotoUrl;
}

