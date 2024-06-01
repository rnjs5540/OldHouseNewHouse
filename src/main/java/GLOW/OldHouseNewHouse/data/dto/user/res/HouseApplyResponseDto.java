package GLOW.OldHouseNewHouse.data.dto.user.res;

import lombok.Builder;

@Builder
public class HouseApplyResponseDto {
    private Long applyId;
    private Long ownerId;
    private Long customerId;
    private String ownerName;
    private String customerName;
    private String ownerPhotoUrl;
    private String customerPhotoUrl;
    private String applyReason;
    private Long houseId;

    private Boolean isOkay;

    public HouseApplyResponseDto(Long applyId, Long ownerId, Long customerId, String ownerName, String customerName, String ownerPhotoUrl, String customerPhotoUrl, String applyReason, Long houseId, Boolean isOkay) {
        this.applyId = applyId;
        this.ownerId = ownerId;
        this.customerId = customerId;
        this.ownerName = ownerName;
        this.customerName = customerName;
        this.ownerPhotoUrl = ownerPhotoUrl;
        this.customerPhotoUrl = customerPhotoUrl;
        this.applyReason = applyReason;
        this.houseId = houseId;
        this.isOkay = isOkay;
    }
}