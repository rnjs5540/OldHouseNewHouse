package GLOW.OldHouseNewHouse.data.entity;

import GLOW.OldHouseNewHouse.Enum.Gate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Users owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Users customer;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "repair", nullable = false)
    private String repair;

    @Column(name = "repair_photo_url", nullable = false)
    private String repairPhotoUrl;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "stay_date", nullable = false)
    private Long stayDate;

    @Column(name = "area", nullable = false)
    private Double area;

    @Column(name = "detail_loc", nullable = false)
    private String detailLoc;

    @Column(name = "gate", nullable = false)
    private Gate gate;

    @Column(name = "is_match")
    private boolean isMatch;

    @Column(name = "is_apply")
    private boolean isApply;

    @Column(name = "is_my_house")
    private boolean isMyHouse;

}
