package GLOW.OldHouseNewHouse.Data.Entity;

import GLOW.OldHouseNewHouse.Data.Dto.User.Req.HouseRequestDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseId;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "repair", nullable = false)
    private String repair;

    @Column(name = "repair_photo_url", nullable = false)
    private String repairPhotoUrl;

    @Column(name = "stay_date", nullable = false)
    private Long stayDate;

    @Column(name = "area", nullable = false)
    private Double area;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "latitude", nullable = false)
    private Long latitude;

    @Column(name = "longitude", nullable = false)
    private Long longitude;

    @Column(name = "detail_loc", nullable = false)
    private String detailLoc;

    @Column(name = "is_okay", nullable = false)
    private Boolean isOkay;

    @Column(name = "gate", nullable = false)
    private HouseRequestDto.Gate gate;

    public House(Long ownerId, Long userId, String repair, String repairPhotoUrl, Long stayDate, Double area, String description,
                 Long latitude, Long longitude, String detailLoc, HouseRequestDto.Gate gate){
        this.ownerId = ownerId;
        this.userId = userId;
        this.repair = repair;
        this.repairPhotoUrl = repairPhotoUrl;
        this.stayDate = stayDate;
        this.area = area;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.detailLoc = detailLoc;
        this.gate = gate;
    }
}
