package GLOW.OldHouseNewHouse.serivce;

import GLOW.OldHouseNewHouse.dto.HouseDTO;
import GLOW.OldHouseNewHouse.entity.House;
import org.springframework.stereotype.Component;

@Component
public class HouseMapper {

    public HouseDTO toDTO(House house) {
        return new HouseDTO(
                house.getOwnerId(),
                house.getUserId(),
                house.getRepair(),
                house.getRepairPhotoUrl(),
                house.getStayDate(),
                house.getArea(),
                house.getDescription(),
                house.getLatitude(),
                house.getLongitude(),
                house.getDetailLoc(),
                house.getIsOkay(),
                house.getGate()
        );
    }

    public House toEntity(HouseDTO houseDTO) {
        return new House(
                houseDTO.getOwnerId(),
                houseDTO.getUserId(),
                houseDTO.getRepair(),
                houseDTO.getRepairPhotoUrl(),
                houseDTO.getStayDate(),
                houseDTO.getArea(),
                houseDTO.getDescription(),
                houseDTO.getLatitude(),
                houseDTO.getLongitude(),
                houseDTO.getDetailLoc(),
                houseDTO.getGate()
        );
    }
}
