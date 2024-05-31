package GLOW.OldHouseNewHouse.entity;

import GLOW.OldHouseNewHouse.dto.HouseDTO;
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

    @Column(name = "repair")
    private String repair;

    @Column(name = "repair_photo_url")
    private String repairPhotoUrl;

    @Column(name = "stay_date")
    private Long stayDate;

    @Column(name = "area")
    private Double area;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private Long latitude;

    @Column(name = "longitude")
    private Long longitude;

    @Column(name = "detail_loc")
    private String detailLoc;

    @Column(name = "is_okay")
    private Boolean isOkay;

    @Column(name = "gate")
    private HouseDTO.Gate gate;

    public House(Long ownerId, Long userId, String repair, String repairPhotoUrl, Long stayDate, Double area, String description,
                 Long latitude, Long longitude, String detailLoc, HouseDTO.Gate gate){
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
