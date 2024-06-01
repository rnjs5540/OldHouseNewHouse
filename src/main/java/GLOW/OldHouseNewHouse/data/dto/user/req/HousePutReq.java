package GLOW.OldHouseNewHouse.data.dto.user.req;

import GLOW.OldHouseNewHouse.Enum.Gate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HousePutReq {
    private String repair;
    private String repairPhoto;
    private Long stayDate;
    private Double area;
    private String description;
    private String detailLoc;
    private Gate gate;
}
