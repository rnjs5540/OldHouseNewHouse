package GLOW.OldHouseNewHouse.Data.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    @Column
    private String refreshToken;

    @Column
    private String email;

    @Column(name = "profile_img_url")
    private String profileImgUrl;

    @Column(name = "match_num")
    private Long matchNum;

    @Column
    private String sex;

    @Column(name = "user_num")
    private String userNum;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_age")
    private String userAge;

    @Builder
    public User(Long id, String refreshToken, String email) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.email = email;
    }
}
