package GLOW.OldHouseNewHouse.serivce;

import GLOW.OldHouseNewHouse.data.dto.user.req.HouseRequestDto;
import GLOW.OldHouseNewHouse.data.entity.House;
import GLOW.OldHouseNewHouse.data.entity.Users;
import GLOW.OldHouseNewHouse.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
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
        //usersRepository에서 owner를 찾아서 넣어줌

        Users owner = usersRepository.findById(houseRequestDto.getOwnerId()).orElse(null);
        if(owner == null){
            log.info("owner가 존재하지 않습니다. 그러나 null이면 안됩니다");
            throw new IllegalArgumentException("owner가 존재하지 않습니다.");
        }


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
