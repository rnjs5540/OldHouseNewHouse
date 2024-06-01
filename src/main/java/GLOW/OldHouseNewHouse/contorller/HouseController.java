package GLOW.OldHouseNewHouse.contorller;

import GLOW.OldHouseNewHouse.data.dto.user.req.HousePostReq;
import GLOW.OldHouseNewHouse.data.dto.user.res.HouseResponseDto;
import GLOW.OldHouseNewHouse.data.dto.user.res.UserGetRes;
import GLOW.OldHouseNewHouse.data.entity.House;
import GLOW.OldHouseNewHouse.data.entity.Users;
import GLOW.OldHouseNewHouse.repository.HouseRepository;
import GLOW.OldHouseNewHouse.repository.UsersRepository;
import GLOW.OldHouseNewHouse.serivce.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
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
@RequiredArgsConstructor
public class HouseController {

    private final HouseRepository houseRepository;
    private final HouseService houseService;
    private final UsersRepository usersRepository;

    @ResponseBody
    @PostMapping("/house")
    public RedirectView addHouse(@RequestBody HousePostReq housePostReq) {
        House house = House.builder()
                .owner(usersRepository.findById(housePostReq.getOwnerId()).orElse(null))
                .title(housePostReq.getTitle())
                .repair(housePostReq.getRepair())
                .repairPhotoUrl(housePostReq.getRepairPhoto())
                .description(housePostReq.getDescription())
                .stayDate(housePostReq.getStayDate())
                .area(housePostReq.getArea())
                .detailLoc(housePostReq.getDetailLoc())
                .gate(housePostReq.getGate())
                .build();

        Long tempId = houseService.registerHouse(house);

        return new RedirectView("/house/" + tempId);
    }

    //집 여러개 list로 받아올때
    @ResponseBody
    @GetMapping("/house")
    public List<House> getHouseList() {
        HouseService HS = new HouseService(houseRepository);
        return HS.getHouseList();
    }

    //집 하나 조회
    @ResponseBody
    @GetMapping("/house/{houseId}")
    public ResponseEntity<HouseResponseDto> getHouse(@PathVariable Long houseId) {

        House house = houseRepository.findById(houseId).orElse(null);
        if(house == null) {
            log.info("집 정보 없음. houseId: {}", houseId);
            return ResponseEntity.status(HttpStatus.GONE).body(null);
        }
        Users owner = usersRepository.findById(house.getOwner().getId()).orElse(null);
        Users customer = usersRepository.findById(house.getCustomer().getId()).orElse(null);

        HouseResponseDto houseResponseDto=HouseResponseDto.builder()
                .houseId(house.getHouseId())
                .ownerId(owner.getId())
                .customerId(customer.getId())
                .title(house.getTitle())
                .description(house.getDescription())
                .repair(house.getRepair())
                .repairPhotoUrl(house.getRepairPhotoUrl())
                .stayDate(house.getStayDate())
                .area(house.getArea())
                .detailLoc(house.getDetailLoc())
                .gate(house.getGate())
                .isMatch(house.isMatch())
                .isApply(house.isApply())
                .isMyHouse(house.isMyHouse())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(houseResponseDto);

    }


    //정보 없으면 요청 못하도록 프론트한데 요구해야 겠음.
    @ResponseBody
    @PutMapping("/house/{houseId}")
    public RedirectView updateHouse(@PathVariable Long houseId, @RequestBody HousePostReq house) {
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
