package GLOW.OldHouseNewHouse.repository;

import GLOW.OldHouseNewHouse.Data.Entity.HouseApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseApplyRepository extends JpaRepository<HouseApply, Long> {
}

