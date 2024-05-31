package GLOW.OldHouseNewHouse.Data.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private String email;

    @Column(name = "profile_img_url", nullable = false)
    private String profileImgUrl;

    @Column(name = "match_num", nullable = false)
    private Long matchNum;

    @Column
    private String sex;

    @Column(name = "user_call_num", nullable = false)
    private String userCallNum;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "Age")
    private Long age;

    @Builder
    public User(Long id, String refreshToken, String email) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.email = email;
    }
}
