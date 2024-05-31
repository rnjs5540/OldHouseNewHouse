package GLOW.OldHouseNewHouse.serivce;

import GLOW.OldHouseNewHouse.Config.Jwt.JwtTokenProvider;
import GLOW.OldHouseNewHouse.data.dto.Auth.Res.AuthGetLoginRes;
import GLOW.OldHouseNewHouse.data.entity.Users;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${kakao.auth.client_id}")
    private String client_id;

    @Value("${kakao.auth.redirect_uri}")
    private String redirect_uri;

    private final GLOW.OldHouseNewHouse.repository.UsersRepository usersRepository;

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<AuthGetLoginRes> login(String code) throws IOException {
        return getKakaoUserIdByKakaoAccessToken(getKakaoAccessToken(code));
    }

    public ResponseEntity<AuthGetLoginRes> login(Authentication authentication) {
        Users users = usersRepository.findById(Long.valueOf(authentication.getName())).orElse(null);

        if(users == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        String accessToken = jwtTokenProvider.createAccessToken(users.getId());
        return new ResponseEntity<>(new AuthGetLoginRes(accessToken, users.getRefreshToken()), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> logout(Authentication authentication){

        Users users = usersRepository.findById(Long.valueOf(authentication.getName())).orElse(null);

        if(users == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        users.setRefreshToken(null);

        usersRepository.save(users);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<AuthGetLoginRes> getKakaoUserIdByKakaoAccessToken(String kakaoAccessToken) throws IOException {
        JsonElement element = getJsonElementByAccessToken(kakaoAccessToken);

        Long id = element.getAsJsonObject().get("id").getAsLong();


        Users users = usersRepository.findById(id).orElse(null);

        if(users == null)
            return register(kakaoAccessToken,id);

        String accessToken = jwtTokenProvider.createAccessToken(id);
        String refreshToken = jwtTokenProvider.createRefreshToken(id);

        users.setRefreshToken(refreshToken);


        usersRepository.save(users);

        return new ResponseEntity<>(new AuthGetLoginRes(accessToken, refreshToken), HttpStatus.OK);
    }

    private ResponseEntity<AuthGetLoginRes> register(String kakaoAccessToken,Long id) throws IOException {
        JsonElement element = getJsonElementByAccessToken(kakaoAccessToken);

        String email= element.getAsJsonObject().get("email").getAsString();
        String accessToken = jwtTokenProvider.createAccessToken(id);
        String refreshToken = jwtTokenProvider.createRefreshToken(id);

        Users users = Users.builder()
                .id(id)
                .email(email)
                .refreshToken(refreshToken)
                .build();

        usersRepository.save(users);

        return new ResponseEntity<>(new AuthGetLoginRes(accessToken, refreshToken), HttpStatus.CREATED);
    }

    private JsonElement getJsonElementByAccessToken(String token) throws IOException {
        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        URL url = new URL(reqUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + token);

        return getJsonElement(httpURLConnection);
    }

    private String getKakaoAccessToken(String code) throws IOException {
        String access_token;
        String reqURL = "https://kauth.kakao.com/oauth/token";

        URL url = new URL(reqURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
        String stringBuilder = "grant_type=authorization_code" +
                "&client_id=" + client_id +
                "&redirect_uri=" + redirect_uri +
                "&code=" + code;
        bufferedWriter.write(stringBuilder);
        bufferedWriter.flush();


        httpURLConnection.getResponseCode();

        JsonElement element = getJsonElement(httpURLConnection);

        access_token = element.getAsJsonObject().get("access_token").getAsString();

        bufferedWriter.close();

        return access_token;
    }

    private JsonElement getJsonElement(HttpURLConnection httpURLConnection) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String line;
        StringBuilder result = new StringBuilder();

        while((line = bufferedReader.readLine()) != null){
            result.append(line);
        }

        bufferedReader.close();

        return JsonParser.parseString(result.toString());
    }

}
