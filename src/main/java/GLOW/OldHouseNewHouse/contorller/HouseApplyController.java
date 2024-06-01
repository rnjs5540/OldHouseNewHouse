package GLOW.OldHouseNewHouse.contorller;

import GLOW.OldHouseNewHouse.Data.Dto.User.Req.HouseApplyRequestDto;
import GLOW.OldHouseNewHouse.Data.Dto.User.Req.HouseApplyResponseDto;
import GLOW.OldHouseNewHouse.Data.Entity.House;
import GLOW.OldHouseNewHouse.Data.Entity.HouseApply;
import GLOW.OldHouseNewHouse.Data.Entity.User;
import GLOW.OldHouseNewHouse.repository.HouseRepository;
import GLOW.OldHouseNewHouse.repository.UserRepository;
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
    UserRepository userRepository;
    @Autowired
    HouseApplyService houseApplyService;

    @ResponseBody
    @PostMapping
    public RedirectView addHouseApply(@RequestBody HouseApplyRequestDto houseApplyRequestDto) {
        HouseApply houseApply = HouseApply.builder()
                .house(houseRepository.findById(houseApplyRequestDto.getHouseId()).orElse(null))
                .user(userRepository.findById(houseApplyRequestDto.getUserId()).orElse(null))
                .applyReason(houseApplyRequestDto.getApplyReason())
                .appealPhotoUrl(houseApplyRequestDto.getAppealPhotoUrl())
                .build();

        Long tempId = houseApplyService.saveApplyForm(houseApply);
        return new RedirectView("/house/" + tempId);
    }

    @GetMapping("/{applyId}")
    public HouseApplyResponseDto getHouseApply(@PathVariable Long applyId) {
        HouseApply houseApply = houseApplyService.getHouseApply(applyId);
        House house = houseRepository.findById(applyId).orElse(null);
        User owner = userRepository.findById(house.getOwnerId()).orElse(null);
        User user = userRepository.findById(houseApply.getHouse().getUserId()).orElse(null);

        return HouseApplyResponseDto.builder()
                .ownerName(owner.getName())
                .userName(user.getName())
                .userAge(user.getAge())
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
