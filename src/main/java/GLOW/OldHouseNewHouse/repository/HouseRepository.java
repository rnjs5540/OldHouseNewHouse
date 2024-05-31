package GLOW.OldHouseNewHouse.repository;

import GLOW.OldHouseNewHouse.Data.Entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long>{
}
