package GLOW.OldHouseNewHouse.serivce;

import GLOW.OldHouseNewHouse.Data.Entity.HouseApply;
import GLOW.OldHouseNewHouse.repository.HouseApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseApplyService {
    @Autowired
    private final HouseApplyRepository houseApplyRepository;

    public Long saveApplyForm(HouseApply houseApplyDto) {
        HouseApply savedHouseApply = houseApplyRepository.save(houseApplyDto);
        return savedHouseApply.getApplyFormId();
    }

    public HouseApply getHouseApply(Long applyFormId) {
        return houseApplyRepository.findById(applyFormId).orElse(null);
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
}
