package GLOW.OldHouseNewHouse.data.entity;

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
    private Long applyId;

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private House house;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Users customer;

    @Column(name = "apply_reason", nullable = false)
    private String applyReason;

    @Column(name = "is_okay", nullable = false)
    @Builder.Default
    private Boolean isOkay = false;
}
