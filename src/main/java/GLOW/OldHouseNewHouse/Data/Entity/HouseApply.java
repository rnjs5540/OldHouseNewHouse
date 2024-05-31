package GLOW.OldHouseNewHouse.Data.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyFormId;

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private House house;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "apply_reason", nullable = false)
    private String applyReason;

    @Column(name = "appeal_photo_url")
    private String appealPhotoUrl;

    @Column(name = "is_okay", nullable = false)
    @Builder.Default
    private Boolean isOkay = false;
}
