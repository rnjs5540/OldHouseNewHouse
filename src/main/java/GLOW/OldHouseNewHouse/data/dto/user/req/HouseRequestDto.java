package GLOW.OldHouseNewHouse.data.dto.user.req;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class HouseRequestDto {
    public enum Gate {
        // 학교 기준 문의 위치
        // 동문, 서문, 정문, 북문, 텍문, 쪽문
        EAST, WEST, MAIN, NORTH, TECH, JJOK
    }

    private Long ownerId;
    private Long userId;
    private String repair;
    private String repairPhotoUrl;
    private Long stayDate;
    private Double area;
    private String description;
    private Long latitude;
    private Long longitude;
    private String detailLoc;
    private Gate gate;
}

