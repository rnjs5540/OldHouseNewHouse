package GLOW.OldHouseNewHouse.serivce;

import GLOW.OldHouseNewHouse.data.dto.user.res.HouseApplyResponseDto;
import GLOW.OldHouseNewHouse.data.entity.House;
import GLOW.OldHouseNewHouse.data.entity.HouseApply;
import GLOW.OldHouseNewHouse.data.entity.Users;
import GLOW.OldHouseNewHouse.repository.HouseApplyRepository;
import GLOW.OldHouseNewHouse.repository.HouseRepository;
import GLOW.OldHouseNewHouse.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseApplyService {
    @Autowired
    private final HouseRepository houseRepository;
    @Autowired
    private final HouseApplyRepository houseApplyRepository;
    @Autowired
    private UsersRepository usersRepository;

    public Long saveApplyForm(HouseApply houseApply) {
        HouseApply savedHouseApply = houseApplyRepository.save(houseApply);
        return savedHouseApply.getApplyId();
    }
    public HouseApply getHouseApply(Long applyId) {
        return houseApplyRepository.findById(applyId).orElse(null);
    }

    public void deleteApplyForm(Long applyFormId) {
        try {
            houseApplyRepository.deleteById(applyFormId);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("해당 신청 폼이 존재하지 않습니다.", e);
        } catch (Exception e) {
            throw new RuntimeException("삭제 중 오류가 발생했습니다.", e);
        }
    }

    public List<HouseApplyResponseDto> getAppliesByHouseId(Long houseId) {
        List<HouseApply> applies = houseApplyRepository.findByHouseId(houseId);
        return applies.stream()
                .map(apply -> new HouseApplyResponseDto(
                                apply.getApplyId(),
                                apply.getHouse().getOwner().getId(),
                                apply.getCustomer().getId(),
                                apply.getHouse().getOwner().getName(),
                                apply.getCustomer().getName(),
                                apply.getHouse().getOwner().getProfileImgUrl(),
                                apply.getCustomer().getProfileImgUrl(),
                                apply.getApplyReason(),
                                apply.getHouse().getId(),
                                apply.getIsOkay()
                        )
                )
                .collect(Collectors.toList());
    }

    public void acceptApply(Long applyId) {
        HouseApply houseApply = houseApplyRepository.findById(applyId).orElse(null);
        assert houseApply != null;
        House house = houseApply.getHouse();
        house.setCustomer(usersRepository.findById(9999999999999999L).orElse(null));
    }
}