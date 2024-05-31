package GLOW.OldHouseNewHouse.serivce;

import GLOW.OldHouseNewHouse.data.dto.user.req.HouseRequestDto;
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

    @Autowired
    private final HouseMapper houseMapper = new HouseMapper();

    public Long registerHouse(HouseRequestDto houseRequestDto) {
        House registerHouse;
        // HouseDTO에서 필요한 정보를 가지고 House 엔티티를 생성
        try {
            registerHouse = houseMapper.toEntity(houseRequestDto);
        }catch(Exception e){
            return null;
        }

        House save = houseRepository.save(registerHouse);
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
    public void updateHouse(Long houseId, HouseRequestDto house) {
        House findHouse = houseRepository.findById(houseId).orElse(null);
        if (findHouse == null) {
            return;
        }

        // 엔티티를 직접 업데이트
        if (house.getRepair() != null) {
            findHouse.setRepair(house.getRepair());
        }
        if (house.getRepairPhotoUrl() != null) {
            findHouse.setRepairPhotoUrl(house.getRepairPhotoUrl());
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
        if (house.getLatitude() != null) {
            findHouse.setLatitude(house.getLatitude());
        }
        if (house.getLongitude() != null) {
            findHouse.setLongitude(house.getLongitude());
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
