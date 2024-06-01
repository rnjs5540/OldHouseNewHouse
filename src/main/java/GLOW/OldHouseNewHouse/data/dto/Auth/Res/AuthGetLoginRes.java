package GLOW.OldHouseNewHouse.data.dto.Auth.Res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthGetLoginRes {

    private String accessToken;

    private String refreshToken;

    private Long id;
}
