package GLOW.OldHouseNewHouse.data.dto.user.req;

import GLOW.OldHouseNewHouse.Enum.Gate;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class HousePostReq {

    private Long ownerId;
    private String title;
    private String repair;
    private String repairPhoto;
    private String description;
    private Long stayDate;
    private Double area;

    private String detailLoc;
    private Gate gate;

}
