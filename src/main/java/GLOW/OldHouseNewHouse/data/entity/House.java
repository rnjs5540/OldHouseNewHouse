package GLOW.OldHouseNewHouse.data.entity;

import GLOW.OldHouseNewHouse.data.dto.user.req.HouseRequestDto;
import GLOW.OldHouseNewHouse.data.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Users owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

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

    @Column(name = "gate", nullable = false)
    private HouseRequestDto.Gate gate;

    public House(Users owner, String repair, String repairPhotoUrl, Long stayDate, Double area, String description,
                 Long latitude, Long longitude, String detailLoc, HouseRequestDto.Gate gate){
        this.owner = owner;
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
