package GLOW.OldHouseNewHouse.serivce;

import GLOW.OldHouseNewHouse.Data.Dto.User.Req.HouseRequestDto;
import GLOW.OldHouseNewHouse.Data.Entity.House;
import org.springframework.stereotype.Component;

@Component
public class HouseMapper {

    public HouseRequestDto toDTO(House house) {
        return new HouseRequestDto(
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

    public House toEntity(HouseRequestDto houseRequestDto) {
        return new House(
                houseRequestDto.getOwnerId(),
                houseRequestDto.getUserId(),
                houseRequestDto.getRepair(),
                houseRequestDto.getRepairPhotoUrl(),
                houseRequestDto.getStayDate(),
                houseRequestDto.getArea(),
                houseRequestDto.getDescription(),
                houseRequestDto.getLatitude(),
                houseRequestDto.getLongitude(),
                houseRequestDto.getDetailLoc(),
                houseRequestDto.getGate()
        );
    }
}
