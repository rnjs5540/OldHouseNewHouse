package GLOW.OldHouseNewHouse.repository;

import GLOW.OldHouseNewHouse.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long>{
}
