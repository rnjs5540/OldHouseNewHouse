package GLOW.OldHouseNewHouse.contorller;

import GLOW.OldHouseNewHouse.data.dto.user.req.HouseApplyRequestDto;
import GLOW.OldHouseNewHouse.data.dto.user.req.HouseApplyResponseDto;
import GLOW.OldHouseNewHouse.data.entity.House;
import GLOW.OldHouseNewHouse.data.entity.HouseApply;
import GLOW.OldHouseNewHouse.data.entity.Users;
import GLOW.OldHouseNewHouse.repository.HouseRepository;
import GLOW.OldHouseNewHouse.serivce.HouseApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RequestMapping("house/apply")
@RestController
public class HouseApplyController {
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    GLOW.OldHouseNewHouse.repository.UsersRepository usersRepository;
    @Autowired
    HouseApplyService houseApplyService;

    @ResponseBody
    @PostMapping
    public RedirectView addHouseApply(@RequestBody HouseApplyRequestDto houseApplyRequestDto) {
        HouseApply houseApply = HouseApply.builder()
                .house(houseRepository.findById(houseApplyRequestDto.getHouseId()).orElse(null))
                .users(usersRepository.findById(houseApplyRequestDto.getUserId()).orElse(null))
                .applyReason(houseApplyRequestDto.getApplyReason())
                .appealPhotoUrl(houseApplyRequestDto.getApplyPhotoUrl())
                .build();

        Long tempId = houseApplyService.saveApplyForm(houseApply);
        return new RedirectView("/house/" + tempId);
    }

    @GetMapping("/{applyId}")
    public HouseApplyResponseDto getHouseApply(@PathVariable Long applyId) {
        HouseApply houseApply = houseApplyService.getHouseApply(applyId);
        House house = houseRepository.findById(applyId).orElse(null);
        Users owner = usersRepository.findById(house.getOwner().getId()).orElse(null);
        Users users = usersRepository.findById(houseApply.getHouse().getUser().getId()).orElse(null);

        return HouseApplyResponseDto.builder()
                .ownerName(owner.getName())
                .userName(users.getName())
                .userAge(users.getAge())
                .applyReason(houseApply.getApplyReason())
                .applyPhotoUrl(houseApply.getAppealPhotoUrl())
                .houseId(houseApply.getHouse().getHouseId())
                .build();
    }

    @DeleteMapping("/{applyId}")
    public void deleteHouseApply(@PathVariable Long applyId) {
        houseApplyService.deleteApplyForm(applyId);
    }
}
