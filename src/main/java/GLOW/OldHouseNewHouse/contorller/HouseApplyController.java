package GLOW.OldHouseNewHouse.contorller;

import GLOW.OldHouseNewHouse.data.dto.user.req.HouseApplyRequestDto;
import GLOW.OldHouseNewHouse.data.dto.user.res.HouseApplyResponseDto;
import GLOW.OldHouseNewHouse.data.entity.House;
import GLOW.OldHouseNewHouse.data.entity.HouseApply;
import GLOW.OldHouseNewHouse.data.entity.Users;
import GLOW.OldHouseNewHouse.repository.HouseApplyRepository;
import GLOW.OldHouseNewHouse.repository.HouseRepository;
import GLOW.OldHouseNewHouse.serivce.HouseApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@RequestMapping("house/apply")
@RestController
public class HouseApplyController {
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    GLOW.OldHouseNewHouse.repository.UsersRepository usersRepository;
    @Autowired
    HouseApplyService houseApplyService;
    @Autowired
    private HouseApplyRepository houseApplyRepository;

    @ResponseBody
    @PostMapping
    public RedirectView addHouseApply(@RequestBody HouseApplyRequestDto houseApplyRequestDto) {
        // House 및 Users 객체를 데이터베이스에서 가져옴
        House house = houseRepository.findById(houseApplyRequestDto.getHouseId())
                .orElseThrow(() -> new RuntimeException("House not found"));
        Users customer = usersRepository.findById(houseApplyRequestDto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // HouseApply 객체 생성 및 설정
        HouseApply houseApply = HouseApply.builder()
                .house(house)
                .customer(customer)
                .applyReason(houseApplyRequestDto.getApplyReason())
                .build();

        // HouseApply 저장 및 ID 반환
        Long tempId = houseApplyService.saveApplyForm(houseApply);

        // 리다이렉트 경로 설정
        return new RedirectView("/house/" + tempId);
    }


    @GetMapping("/{applyId}")
    public HouseApplyResponseDto getHouseApply(@PathVariable Long applyId) {
        HouseApply houseApply = houseApplyService.getHouseApply(applyId);
        House house = houseRepository.findById(applyId).orElse(null);
        assert house != null;
        Users owner = usersRepository.findById(house.getOwner().getId()).orElse(null);
        Users customer = usersRepository.findById(houseApply.getHouse().getUser().getId()).orElse(null);

        assert owner != null;
        assert customer != null;
        return HouseApplyResponseDto.builder()
                .applyId(applyId)
                .ownerId(owner.getId())
                .customerId(customer.getId())
                .ownerName(owner.getName())
                .customerName(customer.getName())
                .ownerPhotoUrl(owner.getProfileImgUrl())
                .customerPhotoUrl(customer.getProfileImgUrl())
                .applyReason(houseApply.getApplyReason())
                .houseId(house.getId())
                .build();
    }

    @DeleteMapping("/{applyId}")
    public void deleteHouseApply(@PathVariable Long applyId) {
        houseApplyService.deleteApplyForm(applyId);
    }

    @GetMapping
    public List<HouseApplyResponseDto> getAppliesByHouseId(@RequestParam Long houseId) {
        return houseApplyService.getAppliesByHouseId(houseId);
    }

    @PostMapping("/{applyId}/yes")
    public RedirectView acceptApply(@PathVariable Long applyId, RedirectAttributes attributes) {
        houseApplyService.acceptApply(applyId);  // 신청서에 체크, 집에도 매칭정보랑 customer기록
        attributes.addAttribute("houseId",
                houseApplyRepository.findById(applyId).orElse(null).getHouse().getId());
        return new RedirectView("/house/{houseId}");
    }

    @PostMapping("/{applyId}/no")
    public RedirectView rejectApply(@PathVariable Long applyId, RedirectAttributes attributes) {
        HouseApply houseApply = houseApplyRepository.findById(applyId).orElse(null);
        attributes.addAttribute("houseId", houseApply.getHouse().getId());
        return new RedirectView("/house/{id}");
    }
}
