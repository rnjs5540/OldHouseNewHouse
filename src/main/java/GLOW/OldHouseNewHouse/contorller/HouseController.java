package GLOW.OldHouseNewHouse.contorller;

import GLOW.OldHouseNewHouse.data.dto.user.req.HouseRequestDto;
import GLOW.OldHouseNewHouse.data.entity.House;
import GLOW.OldHouseNewHouse.repository.HouseRepository;
import GLOW.OldHouseNewHouse.serivce.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.util.List;

@Slf4j
@Controller
public class HouseController {
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    HouseService houseService;

    @ResponseBody
    @PostMapping("/house")
    public ResponseEntity<?> addHouse(@RequestBody HouseRequestDto house) {
        Long tempId = houseService.registerHouse(house);
        if (tempId == null) {
            log.error("house 등록 실패! Users 데이터가 DB 미존재. house: {}", house);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("House 등록에 실패했습니다.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/house/" + tempId)).build();
    }

//    public RedirectView addHouse(@RequestBody HouseRequestDto house) {
//        // HouseService를 이용해서 house를 저장
//        Long tempId = houseService.registerHouse(house);
//        if(tempId == null) {
//            log.error("house 등록 실패! Users 데이터가 DB 미존재. house: {}", house);
//
//            return new RedirectView("/house");
//        }
//        return new RedirectView("/house/" + tempId);
//    }

    @ResponseBody
    @GetMapping("/house")
    public List<House> getHouseList() {
        // HouseService를 이용해서 house 목록을 조회
        HouseService HS = new HouseService(houseRepository);
        return HS.getHouseList();
    }

    @ResponseBody
    @GetMapping("/house/{houseId}")
    public ResponseEntity<House> getHouse(@PathVariable Long houseId) {
        // HouseService를 이용해서 house를 조회
        HouseService HS = new HouseService(houseRepository);
        House findHouse = HS.getHouse(houseId);
        if(findHouse == null) {
            log.info("집 정보 없음. houseId: {}", houseId);
            return ResponseEntity.status(HttpStatus.GONE).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(findHouse);
    }


    //정보 없으면 요청 못하도록 프론트한데 요구해야 겠음.
    @ResponseBody
    @PutMapping("/house/{houseId}")
    public RedirectView updateHouse(@PathVariable Long houseId, @RequestBody HouseRequestDto house) {
        HouseService HS = new HouseService(houseRepository);
        // HouseService를 이용해서 house를 수정
        HS.updateHouse(houseId, house);
        return new RedirectView("/house/" + houseId);
    }


    @ResponseBody
    @DeleteMapping("/house/{houseId}")
    public ResponseEntity<String> deleteHouse(@PathVariable Long houseId) {
        HouseService HS = new HouseService(houseRepository);
        // HouseService를 이용해서 house를 삭제
        try {
            HS.deleteHouse(houseId);
        } catch (Exception e) {
            log.error("집 정보 삭제 실패. houseId: {}", houseId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 집 정보가 없습니다. houseId: " + houseId);
        }
        log.info("집 정보 삭제. houseId: {}", houseId);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
