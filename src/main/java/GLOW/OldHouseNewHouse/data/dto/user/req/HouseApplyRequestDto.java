package GLOW.OldHouseNewHouse.data.dto.user.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HouseApplyRequestDto {
    private Long houseId;
    private Long ownerId;  // jwt로 유저식별하면 이것도 없어도될지도?
    private String applyReason;
}


