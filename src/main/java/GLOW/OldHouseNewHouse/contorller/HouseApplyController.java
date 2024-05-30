package GLOW.OldHouseNewHouse.contorller;

import GLOW.OldHouseNewHouse.dto.HouseApplyRequestDto;
import GLOW.OldHouseNewHouse.dto.HouseApplyResponseDto;
import GLOW.OldHouseNewHouse.entity.HouseApply;
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
    UserRepository userRepository;
    @Autowired
    HouseApplyService houseApplyService;

    @ResponseBody
    @PostMapping
    public RedirectView addHouseApply(@RequestBody HouseApplyRequestDto houseApplyRequestDto) {
        HouseApply houseApply = HouseApply.builder()
                .house(houseRepository.findById(houseApplyRequestDto.getHouseId()).orElse(null))
                .user(userRepository.findById(houseApplyRequest.getUserId()))
                .applyReason(houseApplyRequestDto.getApplyReason())
                .appealPhotoUrl(houseApplyRequestDto.getAppealPhotoUrl())
                .build();

        Long tempId = houseApplyService.saveApplyForm(houseApply);
        return new RedirectView("/house/" + tempId);
    }

    @GetMapping("/{applyId}")
    public HouseApplyResponseDto getHouseApply(@PathVariable Long applyId) {
        HouseApply houseApply = houseApplyService.getHouseApply(applyId);

        User owner = userRepository.findById(houseApply.getHouse().getOwnerId());
        User user = userRepository.findById(houseApply.getHouse().getUserId());

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
