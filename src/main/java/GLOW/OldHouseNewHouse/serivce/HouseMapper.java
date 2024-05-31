package GLOW.OldHouseNewHouse.serivce;

import GLOW.OldHouseNewHouse.data.dto.user.req.HouseRequestDto;
import GLOW.OldHouseNewHouse.data.entity.House;
import GLOW.OldHouseNewHouse.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HouseMapper {
    @Autowired
    UsersRepository usersRepository;

//    public HouseRequestDto toDTO(House house) {
//
//        return new HouseRequestDto(
//                house.getOwner().getId(),
//                house.getUser().getId(),
//                house.getRepair(),
//                house.getRepairPhotoUrl(),
//                house.getStayDate(),
//                house.getArea(),
//                house.getDescription(),
//                house.getLatitude(),
//                house.getLongitude(),
//                house.getDetailLoc(),
////                house.getIsOkay(),
//                house.getGate()
//        );
//    }

    public House toEntity(HouseRequestDto houseRequestDto) {
        return new House(
                usersRepository.findById(houseRequestDto.getOwnerId()).orElse(null),
//                usersRepository.findById(houseRequestDto.getUserId()).orElse(null),
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
