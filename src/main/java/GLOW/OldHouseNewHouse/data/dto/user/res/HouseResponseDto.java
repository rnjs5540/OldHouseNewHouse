package GLOW.OldHouseNewHouse.data.dto.user.res;

import GLOW.OldHouseNewHouse.Enum.Gate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponseDto {
    private Long houseId;
    private Long ownerId;
    private Long customerId;
    private String title;
    private String description;
    private String repair;
    private String repairPhotoUrl;
    private Long stayDate;
    private Double area;
    private String detailLoc;
    private Gate gate;
    private boolean isMatch;
    private boolean isApply;
    private boolean isMyHouse;

}
