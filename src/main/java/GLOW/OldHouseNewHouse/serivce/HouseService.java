package GLOW.OldHouseNewHouse.serivce;

import GLOW.OldHouseNewHouse.data.dto.user.req.HousePostReq;
import GLOW.OldHouseNewHouse.data.dto.user.req.HousePutReq;
import GLOW.OldHouseNewHouse.data.entity.House;
import GLOW.OldHouseNewHouse.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class HouseService {

    private final HouseRepository houseRepository;
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public Long registerHouse(House house) {

        House save = houseRepository.save(house);
        // 생성된 엔티티를 데이터베이스에 저장
        return save.getHouseId();
    }

    public List<House> getHouseList() {
        List<House> all = houseRepository.findAll();
        return all;
    }

    public House getHouse(Long houseId) {
        return houseRepository.findById(houseId).orElse(null);
    }


    @Transactional
    public void updateHouse(Long houseId, HousePutReq house) {
        House findHouse = houseRepository.findById(houseId).orElse(null);
        if (findHouse == null) {
            return;
        }

        // 엔티티를 직접 업데이트
        if (house.getRepair() != null) {
            findHouse.setRepair(house.getRepair());
        }
        if (house.getRepairPhoto() != null) {
            findHouse.setRepairPhotoUrl(house.getRepairPhoto());
        }
        if (house.getStayDate() != null) {
            findHouse.setStayDate(house.getStayDate());
        }
        if (house.getArea() != null) {
            findHouse.setArea(house.getArea());
        }
        if (house.getDescription() != null) {
            findHouse.setDescription(house.getDescription());
        }

        if (house.getDetailLoc() != null) {
            findHouse.setDetailLoc(house.getDetailLoc());
        }
        if (house.getGate() != null) {
            findHouse.setGate(house.getGate());
        }
    }

    public void deleteHouse(Long houseId) {
        try{
            houseRepository.findById(houseId).orElseThrow(() -> new IllegalArgumentException("해당 집이 존재하지 않습니다."));
        } catch (Exception e) {
            throw new IllegalArgumentException("해당 집이 존재하지 않습니다.");
        }
        houseRepository.deleteById(houseId);
    }
}
