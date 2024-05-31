package GLOW.OldHouseNewHouse.Data.Dto.Auth.Res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthGetLoginRes {

    private String accessToken;

    private String refreshToken;
}
